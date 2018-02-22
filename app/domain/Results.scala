package domain

sealed trait CreationResult
case object CreationFail extends CreationResult
case class CreationSuccess(total: Int) extends CreationResult

sealed trait UpdateResult
case class CookieUpdateFail(error: String) extends UpdateResult
case object CookieUpdateSuccess extends UpdateResult
