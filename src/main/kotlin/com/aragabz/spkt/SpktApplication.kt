package com.aragabz.spkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@SpringBootApplication
class SpktApplication

fun main(args: Array<String>) {
	runApplication<SpktApplication>(*args)
}
