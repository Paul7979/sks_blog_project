package at.technikum.blogservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "t_blogposts")
public class BlogPost {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String headline;

  private String fullText;

  private LocalDate publicationDate;

  @ManyToOne
  private Sight sight;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "fk_author")
  private Author author;


}
