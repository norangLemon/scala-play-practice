package domain

import data._
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scala.collection.mutable

class Repository(cookies: mutable.ArrayBuffer[Cookie]) {
  import Repository._

  def getAll: JsValue = {
    val cookieList = cookies.toList
    Json.toJson(cookieList)
  }

  def getCookieIndex(name: String): Int = {
    cookies.indexWhere(_.name == name)
  }

  def getCookie(index: Int): JsValue = {
    Json.toJson(cookies(index))
  }

  def createCookie(name: String): CreationResult = {
    if (getCookieIndex(name) != -1) CreationFail
    else {
      cookies += Cookie(name, 0, Inventory(0, 0, 0), Wallet(0, 0))
      CreationSuccess(cookies.size)
    }
  }

  def parseCookieJson(jsValue: JsValue): ParsingResult = {
    jsValue.validate[Cookie] match {
      case s: JsSuccess[Cookie] => ParsingSuccess(s.get)
      case _: JsError => ParsingFail
    }
  }

  def updateCookie(cookie: Cookie): UpdateResult = {
    val idx = getCookieIndex(cookie.name)
    if (idx == -1) CookieUpdateFail("cookie name $cookie.name does not exist")
    else {
      cookies(idx) = cookie
      CookieUpdateSuccess
    }
  }


}

object Repository {
  def apply: Repository = new Repository(mutable.ArrayBuffer.empty)

  implicit val inventoryWrites: Writes[Inventory] = (
    (JsPath \ "jelly").write[Int] and
      (JsPath \ "chocolate").write[Int] and
      (JsPath \ "cream").write[Int]
    )(unlift(Inventory.unapply))

  implicit val walletWrites: Writes[Wallet] = (
    (JsPath \ "coin").write[Int] and
      (JsPath \ "crystal").write[Int]
  )(unlift(Wallet.unapply))

  implicit val cookieWrites: Writes[Cookie] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "level").write[Int] and
      (JsPath \ "inventory").write[Inventory] and
      (JsPath \ "wallet").write[Wallet]
  )(unlift(Cookie.unapply))

  implicit val inventoryReads: Reads[Inventory] = (
    (JsPath \ "jelly").read[Int] and
      (JsPath \ "chocolate").read[Int] and
      (JsPath \ "cream").read[Int]
    )(Inventory.apply _)

  implicit val walletReads: Reads[Wallet] = (
    (JsPath \ "coin").read[Int] and
      (JsPath \ "crystal").read[Int]
  )(Wallet.apply _)

  implicit val cookieReads: Reads[Cookie] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "level").read[Int] and
      (JsPath \ "inventory").read[Inventory] and
      (JsPath \ "wallet").read[Wallet]
  )(Cookie.apply _)
}

