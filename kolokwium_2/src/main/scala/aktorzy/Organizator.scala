import akka.actor.*

object Organizator {
  case class UstawMaksCyk(maksCyk: Int) {
    assert(maksCyk > 0)
  }
  case object Cyk
  case class PrzejechanaTrasa(liczbaKm: Int)
  case class Init(ilosc: Int)
}



class Organizator extends Actor with ActorLogging {
  import Organizator.*
  def receive = {
    case UstawMaksCyk(mc) =>
      log.info(s"liczba cyknięć do wykonania: $mc")
      val warsztat = context.actorOf(Props[Warsztat](), "Warsztat")
      context.become(poInicjalizacji(mc, List(), warsztat, 0))
    case _ => // inne pomijamy
  }

  def poInicjalizacji(maksCyk: Int, kierowcy: List[ActorRef], warsztat: ActorRef, cyk: Int): Receive = {
    case Cyk =>
      if(cyk<maksCyk)
        log.info("Cyk")
        kierowcy.foreach(el => el ! Kierowca.Cyk)
        warsztat ! Warsztat.Cyk
        val k = cyk+1
        context.become(poInicjalizacji(maksCyk,kierowcy,warsztat,k))
      else{
        context.become(poWyscigu(List(), kierowcy.length))
        kierowcy.foreach(el => el ! Kierowca.PodajTrasę)

      }
    case Init(x) => 

      val kierowcy = List.range(0,x).map(ilosc => {
        context.actorOf(Props(new Kierowca),s"k$ilosc")
      })
      kierowcy.foreach(el => el ! Kierowca.PrzygotujAuto(warsztat))



      context.become(poInicjalizacji(maksCyk, kierowcy, warsztat, cyk))
  }

  def poWyscigu(wyniki: List[Any], i: Int): Receive = {
    case PrzejechanaTrasa(dystans) =>
      val w = wyniki:+(sender().path.name, dystans)
      context.become(poWyscigu(w, i))
      if(wyniki.length==i-1){
        println(wyniki.zipWithIndex)
      }
      
  }

}

