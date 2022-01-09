package at.technikum.blogservice.initializer;

import at.technikum.blogservice.model.Author;
import at.technikum.blogservice.model.Sight;
import at.technikum.blogservice.repository.AuthorRepository;
import at.technikum.blogservice.repository.SightRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record DataInitializer(AuthorRepository authorRepository,
                              SightRepository sightRepository) {

  public void initialize() {
    sightRepository.saveAll(createSights());
    authorRepository.saveAll(createAuthors());
  }

  private static List<Sight> createSights() {
    return List.of(
      Sight.builder().id(1L).name("Stephansdom").location("Wien").build(),
      Sight.builder().id(2L).name("Schönbrunn").location("Wien").build(),
      Sight.builder().id(3L).name("Hofburg").location("Wien").build(),
      Sight.builder().id(4L).name("Schloss Belvedere").location("Wien").build(),
      Sight.builder().id(5L).name("Festung Salzburg").location("Salzburg").build(),
      Sight.builder().id(6L).name("Halstatt").location("Halstatt").build()
    );
  }

  private static List<Author> createAuthors() {
    return List.of(
      Author.builder().id(1L).firstName("Max").lastName("Mustermann").build(),
      Author.builder().id(2L).firstName("Hans").lastName("Huber").build(),
      Author.builder().id(3L).firstName("Gerhard").lastName("Müller").build(),
      Author.builder().id(4L).firstName("Andreas").lastName("Schuster").build()
    );
  }
}
