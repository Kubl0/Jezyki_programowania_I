import akka.actor.{ActorSystem, Actor, ActorRef, Props}

class Szef extends Actor {
  def receive: Receive = {
    case msg => println(s"Odebrałem wiadomość: $msg")
  }
}

class Pracownik extends Actor {
  def receive: Receive = {
    case msg => println(s"Odebrałem wiadomość: $msg")

    case Init(n) => ???
  }
}

case class Init(liczbaPracownikow: Int)
case class Zlecenie(tekst: List[String])




@main
def zadanie_39: Unit = {
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList
  //...
}

