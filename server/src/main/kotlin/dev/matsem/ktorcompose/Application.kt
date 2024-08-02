package dev.matsem.ktorcompose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.use
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
            val width = call.parameters["width"]?.toIntOrNull() ?: 256
            val height = call.parameters["height"]?.toIntOrNull() ?: 256

            call.respondBytes(contentType = ContentType.Image.PNG, status = HttpStatusCode.OK) {
                val scene =
                    ImageComposeScene(
                        width = width,
                        height = height,
                    ) {
                        KtorImage(Modifier.fillMaxSize())
                    }

                val image =
                    scene.use {
                        it
                            .render(nanoTime = 0)
                            .encodeToData(format = EncodedImageFormat.PNG) ?: error("encoding failed")
                    }

                image.bytes
            }
        }
    }
}
