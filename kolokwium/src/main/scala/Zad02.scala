import scala.annotation.tailrec
/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int = 1): List[Any] = {
  
  @tailrec
  def help[A](list: List[A], acc: List[List[A]]=List())(len: Int, shift: Int = 1): List[Any] = {list match{
    case List() => acc
    case head :: tail => {
      help(tail, (acc:+List(list.take(len))))(len, shift)
      
    }
  }
  }
  help(list)(len,shift)
}

@main
def zad02(): Unit ={
  val list = List(1,2,3,4,5)
  val len=3
  val shift=1

  val res=group(list)(len, shift)

  println(res)
}



/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/
