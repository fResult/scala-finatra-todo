package tasks

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class TaskController @Inject() (repo: SortedMapTaskRepository)
    extends Controller {

  get("/health-check") { _: Request => "Hello" }

  post("/todos") { todo: Todo =>
    val createdId = repo.createNewTodo(todo)
    response.created(s"Created Todo with id: $createdId")
  }

  patch("/todos/next") { todo: Todo =>
    val result = repo.moveToDoing(todo)
    result match {
      case Some(value) =>
        response.ok(s"Task with id ${value.id.get} was moved to doing")
      case None => response.notFound("Task was not found")
    }
  }

  get("/todos") { _: Request => repo.getAllItemInTodo }
  get("/doing") { _: Request => repo.getAllItemInDoing }
}
