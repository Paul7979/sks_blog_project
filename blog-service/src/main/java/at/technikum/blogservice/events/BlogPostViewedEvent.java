package at.technikum.blogservice.events;

import at.technikum.blogservice.model.BlogPost;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BlogPostViewedEvent {
  private final BlogPost blogPost;
  private final long timestamp;

  public static BlogPostViewedEvent of(BlogPost blogPost) {
    return new BlogPostViewedEvent(blogPost, System.currentTimeMillis());
  }

}
