import scala.annotation.tailrec

@main
def zad1: Unit = {
  val lista = List(1, 1, 2, 4, 4, 4, 1, 3)

  print(oczyść(lista))
}

def oczyść[A](l: List[A]): List[A] = {
    @tailrec
    def help(l: List[A], acc: List[A]=List()): List[A] = {l match{
      case Nil => acc.reverse
      case head :: Nil => (head::acc).reverse
      case head :: second :: tail if(head==second) => help(second :: tail, acc)
      case head :: second :: tail => help(second::tail, head::acc)
    }
    }
    help(l)
}


@main 
def zad2: Unit = {
  val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')

  println(skompresuj(lista))
}

def skompresuj[A](l: List[A]): List[(A, Int)] = {
    @tailrec
    def help(l: List[A], acc: List[(A, Int)]= Nil, str: String=""): List[(A, Int)] = {l match{
      case Nil => acc.reverse
      case head::tail =>
        tail match{
          case Nil => acc match{
            case acchead::t if acchead!=head => help(tail, (head, (str+head).length) :: acc)
            case _ => help(tail, acc, str+head)
          }
          case h::t if head!=h => help(tail, (head, (str+head).length) :: acc)
          case _ => help(tail, acc, str+head)
        }
      }
    }
    help(l)
}


@main
def zad3: Unit = {
  val l = List(1, 2, 2, 5)
  println(isOrdered(l)(_ < _))
  println(isOrdered(l)(_ <= _))
}

def isOrdered[A](l: List[A])(leq: (A, A) => Boolean): Boolean = {
    @tailrec
    def help(l: List[A])(leq: (A, A) => Boolean): Boolean = {l match{
      case Nil | List(_) => true
      case first :: second :: tail if leq(first, second) => help(second::tail)(leq)
      case first :: second :: tail => false
    }
  }
    help(l)(leq)
}

@main
def zad4: Unit = {
  val l = List(1, 3, 5)
  println(applyForAll(l)(n => n+3))
}

def applyForAll[A, B](l: List[A])(f: A => B): List[B] = {
    @tailrec
    def help(l: List[A], acc: List[B]=Nil)(f: A => B): List[B] = {l match{
      case Nil => acc.reverse
      case head::tail => help(tail, f(head)::acc)(f)
    }
      
    }
    help(l)(f)
}