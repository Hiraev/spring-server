package server.handlers

import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import server.readFile

@Service
class ImageResourceHandler : Handler {
    override fun handle(request: ServerRequest): Mono<ServerResponse> {
        return ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(readFile(request.path()), ByteArray::class.java)
    }
}