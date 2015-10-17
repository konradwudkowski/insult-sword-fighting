package game

import play.api.libs.json.Json

import scala.io.Source
import scala.tools.jline.console.ConsoleReader

case class Entry(id: Int, bossInsult: String, generalInsult: String, comeback: String)

object Entry {
  implicit val reads = Json.reads[Entry]
}

class Repo {
  val entries = {
    val rawInsults = Source.fromURI(getClass.getResource("/insults.json").toURI).mkString
    Json.parse(rawInsults).as[List[Entry]]
  }
}

class GameEngine {
  val repo = new Repo
}

object Application extends App {




  val g = new GameEngine


  Iterator.continually(new ConsoleReader().readLine("> ")).takeWhile(_ != "x").foreach {
    case "s" => println("Starting")
    case "--config" => g.repo.entries.foreach(println)
    case _ => println("Unrecognised command, valida commands are: ...")
  }

  println("Exiting")

  // todo (konrad):  save the state

}
