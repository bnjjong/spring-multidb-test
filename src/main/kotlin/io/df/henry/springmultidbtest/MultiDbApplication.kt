package io.df.henry.springmultidbtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(
)
@EnableScheduling
class MultiDbApplication

fun main(args: Array<String>) {
    runApplication<MultiDbApplication>(*args)
}
