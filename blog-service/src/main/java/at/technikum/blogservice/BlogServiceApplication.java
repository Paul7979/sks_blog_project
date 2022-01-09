package at.technikum.blogservice;

import at.technikum.blogservice.initializer.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class BlogServiceApplication implements CommandLineRunner {

  private final DataInitializer initializer;

  public static void main(String[] args) {
    SpringApplication.run(BlogServiceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    initializer.initialize();
  }
}
