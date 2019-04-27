package server

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    fun getLogger(): Logger = LoggerFactory.getLogger("Logger")

}