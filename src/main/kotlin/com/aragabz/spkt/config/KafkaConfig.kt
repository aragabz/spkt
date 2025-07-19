package com.aragabz.spkt.config

import com.aragabz.spkt.models.User
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConsumerConfig(
    private val kafkaProperties: KafkaProperties
) {
    private fun consumerFactory(): ConsumerFactory<String, User> {
        val configs = kafkaProperties.buildConsumerProperties()
        return DefaultKafkaConsumerFactory(
            configs
        )
    }

    @Bean(name = ["userKafkaListenerContainerFactory"])
    open fun userKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, User>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, User>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.containerProperties.isSyncCommits = true
        return factory
    }

}