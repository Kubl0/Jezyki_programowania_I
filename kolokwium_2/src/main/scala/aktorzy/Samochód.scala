import akka.actor.*

object Samochód {
  case object Dalej
  case class Init(k: ActorRef)
}

class Samochód extends Actor with ActorLogging {
  import Samochód.*
  def receive: Receive = {
    case Init(k) => println(k)

    case Dalej => 
      val r = util.Random
      val speed = r.between(0,100)
      if(speed<=15)
        sender() ! Kierowca.ReakcjaAuta(None)
      else
        sender() ! Kierowca.ReakcjaAuta(Some(speed))
  }
}
