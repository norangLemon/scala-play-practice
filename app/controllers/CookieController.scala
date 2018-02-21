package controllers

import javax.inject._

import play.api.libs.json.{JsError, JsSuccess, JsValue}
import play.api.mvc._

@Singleton
class CookieController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def getCookieList = Action {
    Ok("get list:\n")
  }

  def getCookie(id: String) = Action {
    Ok(id + ": got ID\n")
  }

  /*
   * parse json and update info of cookie
   */
  def updateCookie(id: String) = Action { request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    jsonBody.map { json =>
      val value = (json \ "value").validate[String]
      value match {
        case s: JsSuccess[String] => Ok("update: " + s.get)
        case _: JsError => BadRequest("not including value: " + request.body)
      }
    }.getOrElse {
      BadRequest("format is not json\n")
    }
  }
}
