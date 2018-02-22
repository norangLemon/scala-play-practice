package data

case class Cookie(name: String, level: Int, inventory: Inventory, wallet: Wallet)

case class Inventory(jelly: Int, chocolate: Int, cream: Int)

case class Wallet(coin: Int, crystal: Int)