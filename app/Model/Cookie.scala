package Model

case class Cookie(val name: String, var level: Int, var inventory: Inventory, var wallet: Wallet)

case class Inventory(jelly: Int, chocolate: Int, cream: Int)

case class Wallet(coin: Int, crystal: Int)