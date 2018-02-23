package domain

import data.Cookie

sealed trait CreationResult
case object CreationFail extends CreationResult
case class CreationSuccess(total: Int) extends CreationResult

sealed trait UpdateResult
case class CookieUpdateFail(error: String) extends UpdateResult
case object CookieUpdateSuccess extends UpdateResult

sealed trait ParsingResult
case object ParsingFail extends ParsingResult
case class ParsingSuccess(cookie: Cookie) extends ParsingResult
