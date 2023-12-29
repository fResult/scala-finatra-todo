import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class TaskServerSpec extends FeatureTest {
//  override protected def server: EmbeddedHttpServer = ???
  override val server: EmbeddedHttpServer = new EmbeddedHttpServer(
    new Server
  )
}
