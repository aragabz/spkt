package com.aragabz.spkt.service.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun saveValue(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun getValue(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }
}
