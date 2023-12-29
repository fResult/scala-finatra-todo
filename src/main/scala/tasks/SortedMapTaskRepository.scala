package tasks

import scala.collection.mutable

class SortedMapTaskRepository {
  var todoList = mutable.SortedMap.empty[Int, Todo]
  var doingList = mutable.SortedMap.empty[Int, Doing]

  def createNewTodo(todo: Todo): Int = {
    val lastTodo = todoList.lastOption
    val id = lastTodo match {
      case Some(value) => value._1 + 1
      case None        => 1
    }
    todoList.addOne(id -> Todo(Some(id), todo.detail))
    id
  }

  def moveToDoing(todo: Todo): Option[Doing] = {
    val item = todoList.get(todo.id.get)
    println(s"doings $item")
    val result = item match {
      case Some(value) =>
        todoList.remove(value.id.get)
        val doing = Doing(value.id, value.detail)
        doingList.addOne(doing.id.get -> doing)
        println(s"doings $doingList")
        Some(doingList.last._2)
      case None => None
    }
    result
  }

  def getAllItemInTodo: List[Todo] = todoList.values.toList
  def getAllItemInDoing: List[Doing] = doingList.values.toList
}
