import scala.annotation.tailrec

@main
def zad1: Unit = {
  val napis="abcdef"

  println(reverse(napis))
}


def reverse(str: String) = String {
  @tailrec
  def helper(str: String, acc: String=""): String ={
    if(str.isEmpty) return acc
    
    helper(str.tail, str.head + acc)
  }

  helper(str)

}

@main
def zad2: Unit = {
  println(pierwsza(9))
}


def pierwsza(n: Int): Boolean = {
    @tailrec
    def prime(n: Int, div: Int=2): Boolean ={
      if (n < 2 && n > -2) return false
      if (n==2) return true
      if (n%div==0) return false
      if (div*2 > n) return true

      prime(n, div+1)
    }

    prime(n)
}

@main 
def zad3: Unit = {
  print(ciag(5))
}


def ciag(n: Int): Int = {
  @tailrec
  def fib(n: Int, acc1: Int=0, acc2: Int=1): Int ={
    n match{
    case 0 => acc1
    case 1 => acc2
    case _ => fib(n-1, acc2, acc1+acc2)
    }
  }
  fib(n)
}

def shuffle(l1: List[Int], l2: List[Int]): List[Int] = {
  @tailrec
  def helper(l1: List[Int], l2: List[Int], acc: List[Int] = List()): List[Int] = {
    if (l1.isEmpty && l2.isEmpty) return acc

    if (l2.isEmpty || (l1.nonEmpty && l1.head <= l2.head)) {
      if (acc.nonEmpty && l1.head == acc.last) helper(l1.tail, l2, acc)
      else helper(l1.tail, l2, acc.appended(l1.head))
    } else {
      if (acc.nonEmpty && l2.head == acc.last) helper(l1, l2.tail, acc)
      else helper(l1, l2.tail, acc.appended(l2.head))
    }
  }

  helper(l1, l2)
}

@main
def zad4(): Unit = {
  println(shuffle(List(2, 4, 3, 5), List(1, 2, 2, 3, 1, 5)))
}