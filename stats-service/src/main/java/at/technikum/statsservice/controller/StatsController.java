package at.technikum.statsservice.controller;

import at.technikum.statsservice.model.Sight;
import at.technikum.statsservice.payout.AuthorPayoutService;
import at.technikum.statsservice.service.SightViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "/stats")
@RequiredArgsConstructor
public class StatsController {

  private final SightViewService sightViewService;
  private final AuthorPayoutService authorPayoutService;

  @GetMapping(path = "/topSights/{limit}")
  public Map<Sight, Integer> getTopSights(@PathVariable int limit) {
    return sightViewService.getTopNSightsAndViewsToday(limit);
  }

  @GetMapping(path = "/allSights/{date}")
  public Map<Sight, Integer> getAllSightsForDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
    return sightViewService.getAllSightsStatsForDate(date);
  }

  @GetMapping(path = "/allSights")
  public Map<Sight, Integer> getAllSights() {
    return sightViewService.getAllSightsStatsForToday();
  }

  @GetMapping(path = "/simulatePayout")
  public ResponseEntity<Void> simulatePayout() {
    authorPayoutService.simulatePayoutForThisMonth();
    return ResponseEntity.status(HttpStatus.OK).build();
  }


}
