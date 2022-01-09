package at.technikum.statsservice.payout;

import at.technikum.statsservice.repositories.AuthorClicksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorPayoutService {

  private final AuthorClicksRepository authorClicksRepository;
  private final AuthorClicksPayoutService authorClicksPayoutService;

  @Scheduled(cron = "0 0 0 1 * ?") //Schedules at 1st of every month at 00:00
  public void payout() {
    log.info("Starting payout");
    var start = System.currentTimeMillis();
    LocalDate startDate = LocalDate.now().minusDays(1).withDayOfMonth(1); //1st of last month
    LocalDate endDate = LocalDate.now(); // 1st of this month
    List<LocalDate> dates = startDate.datesUntil(endDate).toList(); // endDate is exclusive
    payout(dates, false);
    log.info("Payout completed duration: {}ms", System.currentTimeMillis() - start);
  }

  public void simulatePayoutForThisMonth() {
    log.info("Starting payout");
    var start = System.currentTimeMillis();
    LocalDate startDate = LocalDate.now().withDayOfMonth(1); //1st of this month
    LocalDate endDate = LocalDate.now(); // today
    List<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1)).toList();
    payout(dates, true);
    log.info("Payout completed duration: {}ms", System.currentTimeMillis() - start);
  }

  private void payout(List<LocalDate> dates, boolean simulate) {
    var authorClicks = dates
      .stream()
      .map(authorClicksRepository::findByDate)
      .flatMap(List::stream)
      .toList();
    if (simulate) {
      authorClicksPayoutService.simulatePayout(authorClicks);
      return;
    }
    authorClicksPayoutService.payout(authorClicks);
  }

}
