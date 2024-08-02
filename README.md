# ktor-compose

This is a sample Ktor server application demonstrating
how to render an image using Compose Multiplatform on server-side.

There is just one endpoint `GET /image` with two optional arguments `width` and `height` which generates
the following image:

![](docs/images/response.png)

The application can be run locally from IDE, or using Docker Compose. The server is exposed on `http://localhost:8080/`.

### Docker

There is a [Dockerfile](Dockerfile) demonstrating how to potentially dockerize such application.

To bring up the stack using Docker Compose:
`docker compose build && docker compose up` or `docker compose up --build`

#### Potential improvements

The current Docker image is Ubuntu-based `eclipse-temurin:17-jre` and needs `mesa-utils` and `libgl1` dependencies to run Compose.
I'd like to experiment with Alpine-based image to bring down the final image size.