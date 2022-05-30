import scala.annotation.tailrec
/*
    Wykorzystując rekurencję (wyłącznie ogonową) zdefiniuj funkcję:

        def countResults[A,B,C](l1: List[A], l2: List[B])(f: (A, B) => C): Set[(C, Int)]

    która zaaplikuje funkcję „f” do elementów l1(i), l2(i), gdzie 0 <= i < min(l1.length, l2.length)
    oraz zwróci zbiór składający się z par:

        (wynik funkcji f, liczba par dla których f zwróciła dany wynik).

    Przykładowo dla:

        countResults(List(1,2,3), List(4,5,4,6))(_+_) == Set((5,1), (7,2))

    ponieważ: 1+4 = 5, 2+5 = 7, 4+3 = 7, 6 pomijamy bo to „nadmiarowy” element w drugiej z list.

    W rozwiązaniu należy skorzystać z mechanizmu dopasowania do wzorca (pattern matching).
    Nie używaj zmiennych ani „pętli” (while, for bez yield, foreach).
*/

def countResults[A,B,C](l1: List[A], l2: List[B])(f: (A, B) => C): Any = {
  @tailrec
  def helper[A, B, C](l1: List[A], l2: List[B], acc: List[C] = List())(f: (A, B) => C): Set[(C, Int)] = (l1,l2) match{
    case (Nil, Nil) => acc.groupBy(element => element).toList.map(el => (el(0),el(1).length)).toSet
    case(head::Nil, Nil) => acc.groupBy(el => el).toList.map(el => (el(0), el(1).length)).toSet
    case(Nil,head::Nil) => acc.groupBy(el => el).toList.map(el => (el(0), el(1).length)).toSet
    case(head::tail,head1::tail1) => helper(tail, tail1, acc:+f(head,head1))(f)
  }
  helper(l1,l2)(f)
  }


@main
def zad1(): Unit = {
  println(countResults(List(1,2,3), List(4,5,4,6))(_+_))
}


/*
  Używając rekurencji ogonowej zdefiniuj funkcję:

    def pairwiseTest[A](l: List[A])(pred: (A, A) => Boolean)

  która sprawdzi, czy predykat „pred" jest spełniony dla wszystkich par
  elementów listy „l” o indeksach (i, długość(l) - i), dla i = 0.. długość(l)/2.

  Przykładowo, dla listy List(1,2,3,4,3,2,1) oraz predykatu równości, sprowadzi
  się to do następującej serii weryfikacji równości:

    l(0) == l(6)
    l(1) == l(5)
    l(2) == l(4)
    l(3) == l(3)

  Ogólnie, seria taka będzie miała postać:

    pred(l(0), l(l.length-1)) == true
    pred(l(1), l(l.length-2)) == true
    pred(l(2), l(l.length-3)) == true
    ...
    pred(l(l.length/2), l(l.length - l.length/2 - 1)) == true

  W przypadku pustej listy funkcja powinna zwrócić true

  Uwaga: w rozwiązaniu nie używaj zmiennych, ani mechanizmów imperatywnych jak pętle.
  Nie używaj też kolekcji języka Scala.
*/



def pairwiseTest[A](l: List[A])(pred: (A, A) => Boolean):Boolean = {
  @tailrec
  def helper[A](l:List[A])(pred: (A, A) => Boolean): Boolean = l match{
    //case head :: cos :: Nil if pred(head, cos) => return true
    case Nil => return true
    case head :: Nil => return true
    case head::tail if (pred(head, tail(tail.length-1))) => helper(tail.dropRight(1))(pred)
    case _ => return false
  }
  helper(l)(pred)
  }


@main
def zad2(): Unit = {
      println(pairwiseTest(List(2,3,4,3,2))((x: Int, y: Int) => x == y))
}

/*
Plik temepratury.txt zawiera w pierwszej kolumnie rok oraz w kolejnych dwunastu kolumnach
średnią temperaturę za każdy miesiąc w danym roku (kolejno: styczeń, luty, marzec itd.).
Dane dotyczące każdego roku rozdzielone są pojedynczymi spacjami.

Przykładowo:

    1779 -4.9 2.2 3.8 9.5 15.4 16.4 17.9 19.5 14.7 9.3 4.1 1.4
    1780 -5.1 -4.3 4.4 5.9 14.2 17.2 19.4 17.9 13.1 9.4 2.8 -4.6
    1781 -4.0 -1.9 1.5 9.1 13.8 19.2 20.1 22.8 16.2 6.0 4.0 -3.6

Zdefiniuj funkcję:

    def maxAvgTemps(data: List[String]): Set[(Int, Double)]

która wyznaczy maksymalną średnią temperaturę dla każdego miesiąca, zwracając zbiór par w formacie (miesiąc, temperatura):

    Set((1,3.5), (2,5.1), (3,7.4), (4,13.2), (5,18.2), (6,22.4), (7,23.5), (8,23.8), (9,16.8), (10,12.6), (11,7.6), (12,3.9))

Rozwiąż to zadanie używając metod oferowanych przez kolekcje. Nie używaj zmiennych, kolekcji
mutowalnych, "pętli" (while, for bez yield, foreach) oraz nie definiuj żadnej funkcji rekurencyjnej.

*/

def maxAvgTemps(data: List[String]): Any = {
    data.map(n => n.split(" ").toList.drop(1)).map(el => el.map(elw => (el.indexOf(elw)+1, elw)))
    .flatten.groupBy(_(0)).toList.map(element => (element(1).maxBy(_(1).toDouble))).sorted
}


@main
def zad3(): Unit = {
  val data = io.Source
    .fromResource("temperatury.txt")
    .getLines()
    .toList
      
  println(maxAvgTemps(data))
        
}


/*
    Korzystając wyłącznie z operacji na kolekcjach (w szczególności nie możesz uzyć rekurencji
    ani mechanizmów imperatywnych, takich jak zmienne i pętle) zdefiniuj funkcję:

        def findPairs(n: Int): Set[(Int,Int)]

    taką, że dowolnej liczby całkowitej N > 1

        findPairs(N)

    zawiera wszystkie pary postaci (p1, p2), gdzie p1 i p2 są liczbami
    pierwszymi takimi, że p1 + p2 = 2 * N oraz p1 <= p2.

*/
def findPairs(n: Int): Set[(Int,Int)] = {
    val listap1 = (1 to n).toList
    val listap2 = (1 to n).toList

    val listan = (1 to n).toList

    def isPrime(n: Int): Boolean = {
      ((1 to n).filter(e => (n%e==0)).size) == 2}

      val helper = for{
        p1 <- listap1
        p2 <- listap2
        N <- listan

        if p1<=p2
        if p1+p2 == N*2
        if isPrime(p1) && isPrime(p2)
      } yield(p1,p2)

      return helper.toSet

} 

@main
def zad4(): Unit = {
        println(findPairs(40))
}


/*
def findPairs(n: Int): Set[(Int,Int)] = {
        val listap1 = (1 to n).toList
        val listap2 = (1 to n).toList       
        val listan = (1 to n).toList  

        def isPrime(num : Int) : Boolean = {
       ((1 to num).filter(e => (num % e == 0)).size) == 2}
        val helper = for{
            p1 <- listap1 
            p2 <- listap2
            N <- listan
            if p1 <= p2 
            if p1 + p2 == 2 * N
            if isPrime(p1) && isPrime(p2)

        } yield (p1, p2)
        return helper.toSet

    }
*/