package model

import play.api.libs.json.Json

import scala.io.Source

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

