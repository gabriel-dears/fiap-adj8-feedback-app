package fiap_adj8.feedback_platform.feedback_app.infra.config.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public PubSubMessageConverter pubSubMessageConverter(ObjectMapper mapper) {
        return new JacksonPubSubMessageConverter(mapper);
    }

}
