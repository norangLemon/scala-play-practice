package controllers

import javax.inject._

import Model.Repository
import play.api.libs.json.{JsError, JsSuccess, JsValue}
import play.api.mvc._

@Singleton
class CookieController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val repository: Repository = Repository.apply

  def getCookieList = Action {
    Ok(repository.getAll)
  }

  def getCookie(id: String) = Action {
    Ok("total number: ")
  }

  /*
   * parse json and update info of cookie
   */
  def updateCookie(id: String) = Action {
    Ok("created: " + repository.create(id))
  }
  def dummy() = Action { request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    jsonBody.map { json =>
      val value = (json \ "value").validate[String]
      value match {
        case s: JsSuccess[String] => Ok("value is: " + s.get)
        case _: JsError => BadRequest("not including value: " + request.body)
      }
    }.getOrElse {
      BadRequest("format is not json\n")
    }
  }
}
