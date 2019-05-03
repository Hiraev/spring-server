package server.handlers

import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import server.readFile
import java.nio.charset.StandardCharsets

@Service
class PageHandler : Handler {

    override fun handle(request: ServerRequest): Mono<ServerResponse> {
        return ok()
                .contentType(MediaType("text", "html", StandardCharsets.UTF_8))
                .header("Content-Encoding", "utf-8")
                .body(readFile(request.path()), ByteArray::class.java)
    }

}