package server.handlers

import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import server.readFile

@Service
class TextResourceHandler : Handler {

    override fun handle(request: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(readFile(request.path()), ByteArray::class.java)

}