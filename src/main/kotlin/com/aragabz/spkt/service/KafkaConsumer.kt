package com.aragabz.spkt.service

import com.aragabz.spkt.extensions.toObject
import com.aragabz.spkt.models.User
import com.aragabz.spkt.service.redis.RedisService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    private val objectMapper: ObjectMapper,
    private val redisService: RedisService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = ["\${kafka.topics.user}"],
        groupId = "\${kafka.consumer.group-id}",
        containerFactory = "userKafkaListenerContainerFactory"
    )
    fun consume(message: String) {
        logger.info("user: $message")
        logger.info(">> convert string to user object")
        val user = message.toObject<User>(objectMapper)
        logger.info(">> save user to redis")
        redisService.saveValue("myUser", user)
        logger.info(">> get user value from redis")
        val redisUser = redisService.getValue("myUser")
        logger.info(">> user: $redisUser")

    }
}