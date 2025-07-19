package com.aragabz.spkt.extensions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

inline fun <reified T> String.toObject(mapper: ObjectMapper): T {
    return mapper.readValue<T>(this)
}