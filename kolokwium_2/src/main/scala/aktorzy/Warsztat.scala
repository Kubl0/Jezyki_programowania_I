import akka.actor.*
object Warsztat {
  case object Cyk
  case class Awaria(auto: ActorRef)
}
class Warsztat extends Actor with ActorLogging {
  import Warsztat.*
  def receive: Receive = {
    case Awaria(auto) =>
      val r = util.Random
      val c = r.between(0,100)

      if(c<20){
        sender() ! Kierowca.WynikNaprawy(None)
      }
      else
        val czas = r.between(1,6)
        context.become(naprawy(auto, 0, czas, sender()))

  }
  def naprawy(auto: ActorRef, czas: Int, czas_p: Int, kierowca: ActorRef): Receive = {
    case Cyk => 
        if(czas<czas_p){
          context.become(naprawy(auto, czas+1, czas_p, kierowca))}
        else
          kierowca ! Kierowca.WynikNaprawy(Some(kierowca))
          context.become(receive)
  }
}
