package Model

import scala.collection.mutable

import play.api.libs.json._
import play.api.libs.functional.syntax._

class Repository {
  import Repository._

  def getAll: JsValue = { ???
  }

  def create(name: String): Int = {
    var newCookie = Cookie(name, 0, Inventory(0, 0, 0), Wallet(0, 0))
    cookies += newCookie
    cookies.size
  }

  def info(name: String) = ???
  def levelUp(name: String) = ???
  def buyItem(name: String, item: String, amount: Int) = ???
  def remove(name: String) = ???
}

object Repository {
  private var cookies: mutable.ArrayBuffer[Cookie] = mutable.ArrayBuffer.empty
  def apply: Repository = new Repository

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
}