package server.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Service
class HeadHandler : Handler {

    override fun handle(request: ServerRequest): Mono<ServerResponse> = ok()
            .header("HeadHeader", "Hello from header")
            .build()

}