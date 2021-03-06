package server.handlers

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface Handler {

    fun handle(request: ServerRequest): Mono<ServerResponse>

}