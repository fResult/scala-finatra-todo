package tasks

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

import scala.collection.mutable

class TaskController extends Controller {
  var todoList = mutable.SortedMap.empty[Int, Todo]

  get("/health-check") { _: Request => "Hello" }

  post("/todos") { todo: Todo =>
    val item = todoList.lastOption
    val id = item match {
      case Some(value) => value._1 + 1
      case None        => 1
    }
    todoList.addOne(id -> Todo(id, todo.detail))
  }

  get("/todos") { _: Request => todoList.values.toList }
}
