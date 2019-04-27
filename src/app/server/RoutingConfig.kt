package server

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import server.handlers.HeadHandler
import server.handlers.ImageResourceHandler
import server.handlers.PostHandler
import server.handlers.TextResourceHandler

@Configuration
class RoutingConfig {

    @Autowired
    lateinit var textResourceHandler: TextResourceHandler

    @Autowired
    lateinit var imageResourceHandler: ImageResourceHandler

    @Autowired
    lateinit var headHandler: HeadHandler

    @Autowired
    lateinit var postHandler: PostHandler

    @Autowired
    lateinit var logger: Logger

    @Bean
    fun routes(): RouterFunction<ServerResponse> = router {
        GET("/text/*") { textResourceHandler.handle(it) }
        GET("/img/*") { imageResourceHandler.handle(it) }
        HEAD("{*}") { headHandler.handle(it) }
        POST("{*}") { postHandler.handle(it) }
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