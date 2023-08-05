package examples

import automorph.Default
import io.circe.generic.auto.*
import java.net.URI
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

private[examples] object Quickstart {
  @scala.annotation.nowarn
  def main(arguments: Array[String]): Unit = {

    // Helper function to evaluate Futures
    def run[T](effect: Future[T]): T = Await.result(effect, Duration.Inf)

    // Define a remote API
    trait Api {
      def hello(some: String, n: Int): Future[String]
    }

    // Create server implementation of the remote API
    val api = new Api {
      override def hello(some: String, n: Int): Future[String] = Future(s"Hello $some $n!")
    }

    // Initialize JSON-RPC HTTP & WebSocket server listening on port 9000 for requests to '/api'
    val server = run(
      Default.rpcServerAsync(9000, "/api").bind(api).init()
    )

    // Define client view of the remote API
    trait Api {
      def hello(some: String, n: Int): Future[String]
    }

    // Initialize JSON-RPC HTTP client for sending POST requests to 'http://localhost:9000/api'
    val client = run(
      Default.rpcClientAsync(new URI("http://localhost:9000/api")).init()
    )

    // Call the remote API function statically
    val remoteApi = client.bind[Api]
    println(run(
      remoteApi.hello("world", 1)
    ))

    // Call the remote API function dynamically
    println(run(
      client.call[String]("hello")("some" -> "world", "n" -> 1)
    ))

    // Close the RPC client
    run(client.close())

    // Stop the RPC server
    run(server.close())
  }
}

