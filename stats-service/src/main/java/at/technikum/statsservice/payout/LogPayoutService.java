package at.technikum.statsservice.payout;

import at.technikum.statsservice.entities.AuthorClicks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogPayoutService implements AuthorClicksPayoutService {

  private static final int pricePerClick = 1;

  @Override
  public void payout(List<AuthorClicks> authorClicks) {
    var clicksPerAuthor = authorClicks
      .stream()
      .collect(Collectors.groupingBy(AuthorClicks::getAuthor)).entrySet()
      .stream()
      .collect(
        Collectors.toMap(
          Map.Entry::getKey,
          authorClicksEntry -> authorClicksEntry
            .getValue()
            .stream()
            .map(AuthorClicks::getViews)
            .mapToInt(Integer::intValue)
            .sum()
        )
      );

    clicksPerAuthor.forEach((key, value) -> log.info("Paying € {} to {} {}", centToFormatted(value), key.getFirstName(), key.getLastName()));
  }

  private String centToFormatted(Integer value) {
    String cents = value % 100 == 0 ? ",-" : "," + value % 100;
    return value / 100 + cents;
  }

  @Override
  public void simulatePayout(List<AuthorClicks> authorClicks) {
    payout(authorClicks);
  }
}
