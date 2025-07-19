package com.aragabz.spkt.service

import com.aragabz.spkt.models.User
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = ["\${kafka.topics.user}"],
        groupId = "\${kafka.consumer.group-id}",
        containerFactory = "userKafkaListenerContainerFactory"
    )
    fun consume(message: String) {
        logger.info("user: ${message}")

    }
}