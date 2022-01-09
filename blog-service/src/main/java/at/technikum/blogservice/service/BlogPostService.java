package at.technikum.blogservice.service;

import at.technikum.blogservice.message.BlogPostEventSender;
import at.technikum.blogservice.model.BlogPost;
import at.technikum.blogservice.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogPostService {

  private final BlogPostRepository blogPostRepository;
  private final BlogPostEventSender eventSender;

  public BlogPost save(BlogPost blogPost) {
    return blogPostRepository.save(blogPost);
  }

  public List<BlogPost> getAll() {
    return blogPostRepository.findAll();
  }

  public BlogPost getById(Long id) {
    var blogPost = blogPostRepository.findById(id);
    blogPost.ifPresent(post -> {
      //log.info("Found blog post {}", post);
      eventSender.viewed(post);
    });
    return blogPost.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}
