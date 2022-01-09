package at.technikum.statsservice.config;

import at.technikum.statsservice.model.BlogPostViewedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  public ConsumerFactory<String, BlogPostViewedEvent> blogPostViewedConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test_1");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(BlogPostViewedEvent.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, BlogPostViewedEvent> blogPostViewedConsumerKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, BlogPostViewedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConcurrency(3);
    factory.setConsumerFactory(blogPostViewedConsumerFactory());
    return factory;
  }


}