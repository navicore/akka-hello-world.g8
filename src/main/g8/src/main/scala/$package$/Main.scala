package $package$

import java.util.Date
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.typesafe.scalalogging.LazyLogging
import $package$.models.{JsonSupport, Message}
import spray.json._
import scala.concurrent.ExecutionContextExecutor

object Main extends LazyLogging with JsonSupport with ErrorSupport {

  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("hello-world-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val route =
      HealthCheck ~
        path(urlpath / Segment) { name =>
          logRequest(s"\$urlpath / \$name") {
            handleErrors {
              cors(corsSettings) {
                get {
                  val response =
                    Message(java.util.UUID.randomUUID(),
                            new Date(),
                            s"hiya \$name")
                  complete(response.toJson.prettyPrint)
                } ~
                  post {
                    decodeRequest {
                      entity(as[Message]) { m =>
                        val response = Message(java.util.UUID.randomUUID(),
                                               new Date(),
                                               s"\${m.body} to you, too!")
                        complete(response.toJson.prettyPrint)
                      }
                    }
                  }
              }
            }
          }
        } ~
        path(urlpath) {
          logRequest(urlpath) {
            handleErrors {
              cors(corsSettings) {
                get {
                  logger.debug(s"get \$urlpath")
                  complete(
                    HttpEntity(ContentTypes.`application/json`,
                               "{\"msg\": \"Say hello to $name$\"}\n"))
                }
              }
            }
          }
        }

    Http().bindAndHandle(route, "0.0.0.0", port)
  }
}

