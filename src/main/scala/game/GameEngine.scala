package game

import model._

class GameEngine {
  val repo = new Repo

  def randomInsults(howMany: Int): List[Insult] = {
    scala.util.Random.shuffle(repo.entries).map(entry => Insult(entry.id, entry.generalInsult)).take(howMany)
  }

  def getComebackForInsult(insult: Insult) = repo.entries.filter(_.id == insult.id).map(
    entry => Comeback(insult.id, entry.comeback)
  )

  def generatePlayer(howMany: Int) = {
    val insults = randomInsults(howMany)
    new Player(insults, insults.flatMap(getComebackForInsult))
  }

  def generatePirate = generatePlayer(3)

  var player: Player = generatePlayer(howMany = 2)
  var pirate: Player = _
  var insult: Insult = _

  def fight() = {
    pirate = generatePirate
    insult = pirate.knownInsults.take(1).head
    println(Console.RED +"Insult: "+Console.RESET + insult.value)
    println(Console.BLUE + "Select your comeback" + Console.RESET)
    player.knownComebacks.foreach { c =>
      println(s"[${c.id}]    ${c.value}")
    }
  }

  def comeback(id: Int) = {
    if (id == insult.id) {
      println(Console.GREEN + "You win this round!" + Console.RESET)
    }else{
      println(Console.RED + "HA HA! Not good enough!" + Console.RESET)
    }

  }
}

