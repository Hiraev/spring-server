package server.handlers

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import server.writeFile

@Service
class PostHandler : Handler {

    override fun handle(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(ByteArray::class.java).doOnSuccess {
            writeFile(request.path(), it)
        }.flatMap {
            ok().header("PostHeader", "Got it!").build()
        }
    }

}