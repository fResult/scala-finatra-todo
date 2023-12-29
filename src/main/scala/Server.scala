import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import tasks._

object TaskServerMain extends Server {}
class Server extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[TaskController]
  }
}