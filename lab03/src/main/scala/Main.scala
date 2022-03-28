import scala.annotation.tailrec

@main
def zad1() : Unit = {
  val lista = List(Some(4.0), Some(9.0), None, Some(1.0), Some(0.0))
  println(sumuj(lista))
}

@main
def zad2(): Unit ={
  val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
  val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
  println(maksimum(lista1, lista2))
}


def sumuj(l : List[Option[Double]]) : Option[Double] = {
  sum(l,0.0)
}



@tailrec
def sum(l : List[Option[Double]], acc : Double) : Option[Double] = {
  l match {
    case Nil => if (acc>0){Some(acc)}else{None}
    case Some(x) :: ogon => if (x>0) {sum(ogon,acc+x)} else{sum(ogon,acc)}
    case _ :: ogon => sum(ogon,acc)
  }
}


def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
  maks(l1,l2)
}

@tailrec
def maks(l1: List[Double], l2: List[Double]) : List[Double] = {
  val l3 = List()

  (l1,l2) match{
    case (Nil,Nil) => Nil
    case (Nil, x) => x
    case (x,Nil) => x
    case(x,y) => if(x>y){x}else{y}

    }
  
  return l3
}