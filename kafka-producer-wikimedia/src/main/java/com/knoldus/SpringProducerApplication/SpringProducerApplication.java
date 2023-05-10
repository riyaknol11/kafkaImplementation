package com.knoldus.SpringProducerApplication;

import com.knoldus.SpringProducerApplication.kafkaProducerService.WikimediaKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProducerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringProducerApplication.class);
    }

    @Autowired
    private WikimediaKafkaProducer wikimediaKafkaProducer;

    @Override
    public void run(String... args) throws Exception {
        wikimediaKafkaProducer.sendMessage();
    }
}
