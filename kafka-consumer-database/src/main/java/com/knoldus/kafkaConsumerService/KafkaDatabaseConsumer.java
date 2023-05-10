package com.knoldus.kafkaConsumerService;

import com.knoldus.kafkaConsumerService.Repository.WikimediaRepository;
import com.knoldus.kafkaConsumerService.entity.WikimediaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    @Autowired
    private WikimediaRepository wikimediaRepository;

    private static final Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    @KafkaListener(
            topics = "wikimedia",
            groupId = "streamGroup"
    )
    public void consume(String messageEvent){
        logger.info(String.format("Event message received --> %s", messageEvent));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(messageEvent);

        wikimediaRepository.save(wikimediaData);
    }

}
