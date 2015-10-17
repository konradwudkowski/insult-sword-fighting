package game

import model._

class GameEngine {
  val repo = new Repo
  def randomInt = scala.util.Random.nextInt(repo.entries.size)
  def randomInsults(howMany: Int) = repo.entries.map(entry => Insult(entry.id, entry.generalInsult)).take(howMany) // make it actually random
  def getComebackForInsult(insult: Insult) = repo.entries.filter( _.id == insult.id).map(entry => Comeback(insult.id,entry.comeback))

  def generatePlayer(howMany: Int) = {
    val insults = randomInsults(howMany)
    new Player(insults, insults.flatMap(getComebackForInsult))
  }

  def generatePirate = generatePlayer(3)

  var player: Player = generatePlayer(howMany = 2)
  var pirate: Player = _

  def fight() = {
    pirate = generatePirate
    println(pirate.knownInsults.map(_.value).take(1))
    println("Select your comeback")
    player.knownComebacks.foreach { c =>
      println(s"[${c.id}]    ${c.value}")
    }
  }
}

