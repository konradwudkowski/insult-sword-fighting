package game

import scala.tools.jline.console.ConsoleReader

object Application extends App {

  val g = new GameEngine

  val selectOptionRegex = """^(\d+)""".r

  Iterator.continually(new ConsoleReader().readLine("> ")).takeWhile(_ != "x").foreach {
    case "s" => {
      println("Starting game for new player")
      g.fight()
    }
    case selectOptionRegex(option) => {
      g.comeback(option.toInt)
    }
    case "--config" => g.repo.entries.map(_.generalInsult).foreach(println)
    case _ => println("Unrecognised command, valida commands are: ...")
  }

  println("Exiting")

  // todo (konrad):  save the state

}
