package com.example.L18kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class KafkaController {

    private static Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate; // here <String==> key, String==> msg/data to push/produce>
                                                        // key provided, so that messages for that key goes into same partition,
                                                        // it is used when we need to maintain order for some messages
                                                        // like user should get 1st signup email then only job-apply email
                                                        // from a job portal site

    @GetMapping
    public ResponseEntity<String> publish(@RequestParam String msg) throws ExecutionException, InterruptedException {

        // pushes/produces the message in "demo-topic"
        ListenableFuture<SendResult<String, String>> kafkaResponseFuture =  kafkaTemplate.send("demo-topic",msg);

        logger.info("Data pushed : {}", kafkaResponseFuture.get());
//        return ResponseEntity.ok().build();
        return ResponseEntity.ok().body("Published");
    }

    @GetMapping("/2partition")
    public ResponseEntity<String> publishTo2partition(@RequestParam String msg, @RequestParam String userId) throws ExecutionException, InterruptedException {

        // here userId used as key (see def of send()), i.e., msg with same userId goes in same partition,
        // i.e., order of the messages is maintained for the same user.
        ListenableFuture<SendResult<String, String>> kafkaResponseFuture =  kafkaTemplate.send("jbdl-31-2part-2rep", userId, msg);

        logger.info("Data pushed : {}", kafkaResponseFuture.get());
        return ResponseEntity.ok().body("Published");
    }
}
