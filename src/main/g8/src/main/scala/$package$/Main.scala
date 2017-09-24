package $package$

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import $package$.models.{JsonSupport, Message}
import $package$.routes.{$httpBasePath;format="Camel"$Route, $httpBasePath;format="Camel"$SegmentRoute}
import scala.concurrent.ExecutionContextExecutor

object Main extends LazyLogging with JsonSupport with ErrorSupport {

  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("$name;format="Camel"$-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val route =
      HealthCheck ~
      $httpBasePath;format="Camel"$Route.apply ~
      $httpBasePath;format="Camel"$SegmentRoute.apply

    Http().bindAndHandle(route, "0.0.0.0", port)
  }
}

