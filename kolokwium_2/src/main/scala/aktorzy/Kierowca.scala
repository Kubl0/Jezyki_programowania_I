import akka.actor.*

object Kierowca {
  case class Init(k: List[ActorRef])
  case object Cyk
  case class PrzygotujAuto(warsztat: ActorRef)
  case class ReakcjaAuta(ov: Option[Int])
  case object PodajTrasę
  case class WynikNaprawy(efekt: Option[ActorRef])

  var km = 0
}

class Kierowca extends Actor with ActorLogging {
  import Kierowca.*
  def receive: Receive = {
    case PrzygotujAuto(warsztat) => 
      val auto = context.actorOf(Props[Samochód](), "auto")

      context.become(wyscig(auto, 0, warsztat))
  }
  def wyscig(auto: ActorRef, dystans:Int, warsztat: ActorRef): Receive ={

    case Cyk =>
      auto ! Samochód.Dalej
    
    case ReakcjaAuta(ov) => 
      if(ov==None){
        warsztat ! Warsztat.Awaria(auto)
        context.become(popsutysamochod(auto,dystans,warsztat))
      }
      else
        val v = ov.get + dystans
        context.become(wyscig(auto,v, warsztat))



  }

  def popsutysamochod(auto: ActorRef, dystans: Int, warsztat: ActorRef): Receive ={
    case WynikNaprawy(result) => 
      if(result.getOrElse(None)!=None){
        context.become(wyscig(auto,dystans,warsztat))
      }
      else {
        context.become(koniecwyscigu(dystans))}
    
    case PodajTrasę =>
      sender() ! Organizator.PrzejechanaTrasa(dystans)
  }

  def koniecwyscigu(dystans: Int):Receive ={
    case PodajTrasę => 
      sender() ! Organizator.PrzejechanaTrasa(dystans)
  }
}
