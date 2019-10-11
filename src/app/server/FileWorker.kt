package server

import org.springframework.beans.factory.annotation.Value
import reactor.core.publisher.Mono
import java.io.File

@Value("\${img.path}")
var imgPath: String = "img/"

fun readFile(path: String): Mono<ByteArray> = Mono.fromCallable {
    File(path.removePrefix("/")).readBytes()
}
        .onErrorResume { Mono.empty() }

fun writeFile(path: String, data: ByteArray) {

    File("$imgPath$path").run {
        if (!exists()) {
            parentFile.mkdirs()
            createNewFile()
        }
        writeBytes(data)
    }

}