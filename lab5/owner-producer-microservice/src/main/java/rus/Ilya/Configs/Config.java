package rus.Ilya.Configs;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class Config {

    @Value("${owner_topic.create}")
    private String createTopicName;

    @Value("${owner_topic.update}")
    private String updateTopicName;

    @Value("${owner_topic.deleteById}")
    private String deleteByIdTopicName;

    @Value("${owner_topic.deleteByEntity}")
    private String deleteByEntityTopicName;

    @Value("${owner_topic.deleteByAll}")
    private String deleteByAllTopicName;

    @Value("${owner_topic.getById}")
    private String getByIdTopicName;

    @Value("${owner_topic.getAll}")
    private String getAllTopicName;


    @Value("${owners_topic.responseGetAll}")
    private String responseGetAllTopicName;

    @Value("${owners_topic.response}")
    private String responseName;
    private final KafkaProperties kafkaProperties;


    @Autowired
    public Config(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return
                new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder
                .name(createTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic responseGetAllTopic() {
        return TopicBuilder
                .name(responseGetAllTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean

    public NewTopic responseTopic() {
        return TopicBuilder
                .name(responseName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic updateTopic() {
        return TopicBuilder
                .name(updateTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }


    @Bean
    public NewTopic deleteByIdTopic() {
        return TopicBuilder
                .name(deleteByIdTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteByEntityTopic() {
        return TopicBuilder
                .name(deleteByEntityTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteAllTopic() {
        return TopicBuilder
                .name(deleteByAllTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getByIdTopic() {
        return TopicBuilder
                .name(getByIdTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getAll() {
        return TopicBuilder
                .name(getAllTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
