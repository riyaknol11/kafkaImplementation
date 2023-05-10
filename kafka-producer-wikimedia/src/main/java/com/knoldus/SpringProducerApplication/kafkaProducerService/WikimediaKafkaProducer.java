package com.knoldus.SpringProducerApplication.kafkaProducerService;

import com.knoldus.SpringProducerApplication.eventHandler.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikimediaKafkaProducer.class);
    @Autowired
     private KafkaTemplate<String,String> kafkaTemplate;

    //creating a method to read the real time stream data from wikimedia, we use Event Source Handler
    //3 dependencies are added --> eventsource, json core, jackson databind
    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia";

        // It creates an EventHandler object (implemented in the WikimediaChangesHandler class),
        // which is responsible for handling events received from the Wikimedia stream.

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();

        // It also creates an EventSource object, which connects to the Wikimedia stream and starts receiving events.

        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }


}
