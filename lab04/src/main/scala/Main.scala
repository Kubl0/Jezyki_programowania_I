import scala.annotation.tailrec
import scala.compiletime.ops.boolean

@main
def zad1: Unit = {
  val lista = List(Some(4.0), Some(-3.0), None, Some(1.0), Some(0.0))

  println(sumuj(lista))
}


def sumuj(l: List[Option[Double]]): Option[Double] = {
  @tailrec
  def help(l: List[Option[Double]], acc: Double=0): Option[Double] = l match{
    case List() => Some(acc)
    case head :: tail => 
      if(head.getOrElse(0.0) > 0) help(tail, acc+head.get)
      else help(tail, acc)
  }

  help(l)
}

@main
def zad2: Unit = {
  val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
  val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)

  println(maksimum(lista1, lista2))
}

def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    @tailrec
    def help(l1: List[Double], l2: List[Double], acc: List[Double]=List()): List[Double] = {(l1,l2) match{
      case(Nil, Nil) => acc
      case(Nil, List(_*)) => acc.appendedAll(l2)
      case(List(_*), Nil) => acc.appendedAll(l1)
      case (head1 :: tail1, head2 :: tail2) => 
        if (head1>head2) help(tail1,tail2, acc :+ head1)
        else help(tail1,tail2, acc :+ head2)
      }
    }
  help(l1,l2)
}

@main
def zad3: Unit = {
  val lista = List(2, 1, 4, 1, 3, 3, 1, 2)

  println(usun(lista, 1))
}

def usun[A](l: List[A], el: A): List[A] = {
    @tailrec
    def help(l: List[A], el: A, res: List[A]=Nil): List[A] = {l match{
      case Nil => res
      case head :: tail => 
        if (head==el) help(tail, el, res)
        else help(tail, el, res :+ head)
    }

    }

    help(l, el)
}

@main
def zad4: Unit = {
  val lista = List(1, 3, 5, 6, 7)

  print(divide(lista))
}

def divide[A](l: List[A]): (List[A], List[A]) = {
    @tailrec
    def help(l: List[A], index: Int=0, acc: (List[A], List[A])=(Nil, Nil)): (List[A], List[A]) = {l match
      case Nil => acc
      case head :: tail =>
        if(index%2==0)
          help(tail, index+1, (acc(0) :+ head, acc(1)))
        else
          help(tail, index+1, (acc(0), acc(1) :+ head))
    }
    help(l)
}


@main
def zad5: Unit = {
  type Pred[A] = A => Boolean

  def and[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    x:A => p(x) && q(x)
  }

  def or[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    x:A => p(x) || q(x)
  }

def not[A](p: Pred[A]): Pred[A] = {
    x:A => !p(x)
  }

def imp[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    x:A => !p(x) || q(x)
  }
}
