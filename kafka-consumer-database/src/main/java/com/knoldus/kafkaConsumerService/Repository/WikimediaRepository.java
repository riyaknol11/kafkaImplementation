package com.knoldus.kafkaConsumerService.Repository;

import com.knoldus.kafkaConsumerService.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaRepository extends JpaRepository<WikimediaData, Long> {

}
