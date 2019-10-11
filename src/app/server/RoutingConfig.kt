package server

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import server.handlers.ImageResourceHandler

@Configuration
class RoutingConfig {

    @Autowired
    lateinit var logger: Logger

    @Autowired
    lateinit var imageResourceHandler: ImageResourceHandler

    @Bean
    fun routes(): RouterFunction<ServerResponse> = router {
        GET("/img/**") { imageResourceHandler.handle(it) }
    }.filter { request, next ->

        logger.info("${request.method()} ${request.path()}")

        next.handle(request).doAfterSuccessOrError { response, error ->
            error?.run {
                logger.info("${error.message}")
            } ?: run {
                logger.info("${response.statusCode().name} ${response.statusCode().value()}")
            }
        }

    }

}