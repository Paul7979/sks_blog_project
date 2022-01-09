package at.technikum.statsservice.payout;

import at.technikum.statsservice.entities.AuthorClicks;

import java.util.List;

public interface AuthorClicksPayoutService {

  void payout(List<AuthorClicks> authorClicks);

  void simulatePayout(List<AuthorClicks> authorClicks);

}
