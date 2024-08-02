package dev.matsem.imagen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondBytes
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.skia.EncodedImageFormat

fun main() {
    System.setProperty("java.awt.headless", "true")
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
    ).start(wait = true)
}

fun Application.module() {
    routing {
        get("/image") {
            call.respondBytes(contentType = ContentType.Image.PNG, status = HttpStatusCode.OK) {
                val scene =
                    ImageComposeScene(
                        width = 256,
                        height = 256,
                    ) {
                        KtorImage(Modifier.fillMaxSize())
                    }

                val image =
                    scene
                        .render(nanoTime = 0)
                        .encodeToData(format = EncodedImageFormat.PNG) ?: error("encoding failed")

                image.bytes
            }
        }
    }
}
