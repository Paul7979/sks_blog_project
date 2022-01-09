package at.technikum.statsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StatsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(StatsServiceApplication.class, args);
  }
}
