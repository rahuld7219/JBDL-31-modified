package com.example.L18kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka // this will create a thread group that will listen to a particular topic
@Configuration
public class KafkaConsumerConfig {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    // This method will be executed whenever the consumer gets any data from the specified topic.
    @KafkaListener(topics = "demo-topic", groupId = "email")
    public void listenKafkaTopic(String message) { // the message will be the message produced to the specified topic
        logger.info("Consuming : {}", message);
    }
}
