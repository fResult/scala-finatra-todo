package tasks

trait Task {
  val id: Option[Int] = None
  val detail: String
}

case class Todo(override val id: Option[Int] = None, detail: String)
    extends Task
case class Doing(override val id: Option[Int] = None, detail: String)
    extends Task
case class Done(override val id: Option[Int] = None, detail: String)
    extends Task
