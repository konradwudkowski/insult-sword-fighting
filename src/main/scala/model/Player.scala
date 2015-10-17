package model

case class Insult(id: Int, value: String)
case class Comeback(id: Int, value: String)

case class Player(knownInsults: List[Insult], knownComebacks: List[Comeback])


