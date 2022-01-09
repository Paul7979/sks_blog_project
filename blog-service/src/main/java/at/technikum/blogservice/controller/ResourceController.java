package at.technikum.blogservice.controller;

import at.technikum.blogservice.model.Author;
import at.technikum.blogservice.model.BlogPost;
import at.technikum.blogservice.model.Sight;
import at.technikum.blogservice.repository.AuthorRepository;
import at.technikum.blogservice.repository.SightRepository;
import at.technikum.blogservice.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/resources")
@RequiredArgsConstructor
public class ResourceController {

  private final AuthorRepository authorRepository;
  private final BlogPostService blogPostService;
  private final SightRepository sightRepository;


  @PostMapping(path = "blogPosts")
  public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
    blogPost.setId(null);
    var createdBlogPost = blogPostService.save(blogPost);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost);
  }

  @GetMapping(path = "/blogPosts")
  public List<BlogPost> getAllPosts() {
    return blogPostService.getAll();
  }

  @GetMapping(path = "/blogPosts/{id}")
  public BlogPost getPostById(@PathVariable Long id) {
    return blogPostService.getById(id);
  }

  @GetMapping(path = "/authors")
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @GetMapping(path = "/sights")
  public List<Sight> getAllSights() {
    return sightRepository.findAll();
  }

}
