package server

import org.springframework.beans.factory.annotation.Value
import reactor.core.publisher.Mono
import java.io.File

@Value("\${media.path}")
var mediaPath: String = "media"

fun readFile(path: String): Mono<ByteArray> = Mono.fromCallable { File("$mediaPath$path").readBytes() }
        .onErrorResume { Mono.empty() }

fun writeFile(path: String, data: ByteArray) {

    File("$mediaPath$path").run {
        if (!exists()) {
            parentFile.mkdirs()
            createNewFile()
        }
        writeBytes(data)
    }

}