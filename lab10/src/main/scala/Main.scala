import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Piłeczka
case class Graj35(przeciwnik: ActorRef)


class Gracz35 extends Actor {
  
  def receive: Receive = {
    case Graj35(actor) => actor ! Piłeczka
    case Piłeczka => 
      if(self.path.name=="p1") println("Ping")
      else println("Pong")

      sender() ! Piłeczka
  }
}

@main
def zad1(): Unit = {
  val system = ActorSystem("PingPong")
  val p1 = system.actorOf(Props[Gracz35](),"p1")
  val p2 = system.actorOf(Props[Gracz35](), "p2")

  p1 ! Graj35(p2)
}


case class Piłeczka36(max: Int, i: Int)
case class Graj36(przeciwnik: ActorRef, maks: Int)


class Gracz36 extends Actor {
  
  def receive: Receive = {
    case Graj36(actor, max) => actor ! Piłeczka36(max,1)
    case Piłeczka36(max, i) => 
      if(i>max) context.system.terminate()
      else{
        if(self.path.name=="p1") println("Ping")
        else println("Pong")

        sender() ! Piłeczka36(max, i+1)
      }
  }
}

@main
def zad2(): Unit = {
  val system = ActorSystem("PingPong")
  val p1 = system.actorOf(Props[Gracz36](),"p1")
  val p2 = system.actorOf(Props[Gracz36](), "p2")

  p1 ! Graj36(p2, 10)
}


case class Piłeczka37(nextplayer: ActorRef, nextplayer2: ActorRef) 
case class Graj37(nextplayer: ActorRef, nextplayer2: ActorRef)

class Gracz37 extends Actor {
   
   def receive: Receive = {
    case Graj37(nextplayer,nextplayer2) => nextplayer ! Piłeczka37(nextplayer2, self)
    case Piłeczka37(nextplayer, nextplayer2) => 

      println(self.path.name)

      nextplayer ! Piłeczka37(nextplayer2, self)

  }
}

@main
def zad3(): Unit = {
  val system = ActorSystem("PingPong")
  val p1 = system.actorOf(Props[Gracz37](),"p1")
  val p2 = system.actorOf(Props[Gracz37](), "p2")
  val p3 = system.actorOf(Props[Gracz37](), "p3")

  p1 ! Graj37(p2, p3)
}

case class Piłeczka38(players: List[ActorRef], n: Int)
case class Graj38(players: List[ActorRef], n: Int)

class Gracz38 extends Actor {
  def receive: Receive = {
    case Graj38(players, n) => players(0) ! Piłeczka38(players, n)
    case Piłeczka38(players,n) => 
      println(self.path.name)

      if(n+1>players.length-1) players.head ! Piłeczka38(players,0)
      else players(n+1) ! Piłeczka38(players, n+1)
  }
}

@main 
def zad4(): Unit = {
  val system = ActorSystem("PingPong")

  println("Podaj ilość graczy >> ")
  val n = io.StdIn.readLine().toInt

  val players = List.range(0, n).map(n => {
    system.actorOf(Props[Gracz38](), s"p${n+1}")
  })

  players.head ! Graj38(players, 0)
}