import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class TaskServerSpec extends FeatureTest {
//  override protected def server: EmbeddedHttpServer = ???
  override val server: EmbeddedHttpServer = new EmbeddedHttpServer(
    new Server
  )

  test("health-check should respond Hello") {
    server.httpGet("/health-check", andExpect = Status.Ok)
  }

  test("Created first Todo should return status created with id equal to 0") {
    server.httpPost(
      path = "/todos",
      postBody = """
        |{
        | "id": null,
        | "detail": "Buy banana"
        |}
      """.stripMargin,
      andExpect = Status.Created,
      withJsonBody = "Created Todo with id: 1"
    )
  }

  test(
    "Move Task to doing should return status created and json body is 'Task with id 1 was moved to doing'"
  ) {
    server.httpPatch(
      path = "/todos/next",
      patchBody = """
        |{
        | "id": 1,
        | "detail": "Buy banana"
        |}
      """.stripMargin,
      andExpect = Status.Ok,
      withJsonBody = "Task with id 1 was moved to doing"
    )
  }

  test("After move task to doing, todo list should be empty") {
    server.httpGet(path = "/todos", withJsonBody = "[]")
  }

  test("After move task to doing, doing list should contain 1 element") {
    server.httpGet(
      path = "/doings",
      withBody = """
        |{
        | "id": 0,
        | "detail": "Buy banana"
        |}
      """.stripMargin
    )
  }
}
