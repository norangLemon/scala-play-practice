package presentation.controllers

import javax.inject._

import play.api.mvc._
import domain._


@Singleton
class CookieController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val repository: Repository = Repository.apply

  def getCookieList = Action {
    Ok(repository.getAll)
  }

  def getCookie(id: String) = Action {
    val index = repository.getCookieIndex(id)
    index match {
      case -1 => BadRequest("cookie name " + id + " is not exist")
      case _ => Ok(repository.getCookie(index))
    }
  }

  def createCookie(id: String) = Action {
    repository.createCookie(id) match {
      case CreationFail => BadRequest("cookie name $id already exists.")
      case CreationSuccess(n) => Ok("new cookie is created! the number of cookies is: $n")
    }
  }

  /*
  def updateCookie(id: String) = Action { request: Request[AnyContent] =>
    val result = repository.updateCookie(request)
    result match {
      case CookieUpdateFail(error) => BadRequest(error)
      case CookieUpdateSuccess => Ok(json)
    }
  }
  */
}
