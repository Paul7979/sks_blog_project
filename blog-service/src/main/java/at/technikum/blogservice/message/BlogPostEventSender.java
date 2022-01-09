package at.technikum.blogservice.message;

import at.technikum.blogservice.events.BlogPostViewedEvent;
import at.technikum.blogservice.model.BlogPost;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostEventSender {

  private final Source source;

  public void viewed(BlogPost blogPost) {
    var message = MessageBuilder
      .withPayload(BlogPostViewedEvent.of(blogPost))
      .build();
      source.output().send(message);
  }

}
