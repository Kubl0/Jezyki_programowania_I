import akka.actor.{ActorSystem, Actor, ActorRef, Props}
case class Zlecenie(tekst: List[String])
case class Init(liczbaPracowników: Int)
case class Wykonaj(linia: String)
case class Wynik(ilosc_slow: Int)
class Szef extends Actor {
  def pracujacy(acc: Int, pracownicy: List[ActorRef], ilosc_lini: Int): Receive = {
    case Wynik(ilosc_slow) => {
      val nowaIloscLinii = ilosc_lini - 1
      var nowyAcc = acc + ilosc_slow
      // agregacja
      context.become(pracujacy(nowyAcc, pracownicy, ilosc_lini - 1))
      if (nowaIloscLinii == 0) {
        println(f"przyszła ostatnia linia od ${sender().path.name}: ${ilosc_slow}, czyli slow w pliku jest ${nowyAcc}")
        context.become(gotowy(pracownicy))
      } else {
        println(f"Wynik od ${sender().path.name}: ${ilosc_slow}, czekam jeszcze na ${nowaIloscLinii} linii, aktualnie wiem o ${nowyAcc} slowach")
      }
    }
  }
  def gotowy(pracownicy: List[ActorRef]): Receive = {
    case Zlecenie(linie) => {
      linie.zipWithIndex.map(el => (el._1, el._2%pracownicy.length))
      .foreach(el => pracownicy(el._2) ! Wykonaj(el._1))

      context.become(pracujacy(0, pracownicy, linie.length))
    }
  }
  def receive: Receive = {
    case Init(liczbaPracowników: Int) => {
      val pracownicy: List[ActorRef] = Range(0, liczbaPracowników)
      .toList
      .map(el => context.actorOf(Props[Pracownik](), "pracownik" + el.toString))
      // zapamiętywanie pracowników
      context.become(gotowy(pracownicy))      
    }
  }
}

class Pracownik extends Actor {
  def receive: Receive = {
    case Wykonaj(linia) => {
      Thread.sleep(scala.util.Random.nextInt(1000))
      sender() ! Wynik(linia.split(" ").length)
    }
  }
}
@main
def zadanie_39: Unit = {
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList
      .filter(_!="")

  val system = ActorSystem("biuro")
  val szef: ActorRef = system.actorOf(Props[Szef](), "szef")
  szef ! Init(10)
  szef ! Zlecenie(lista)
}