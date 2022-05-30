
@main
def zad1: Unit = {
  def sumOpts(l: List[Option[Double]]): Option[Double] = {
    if l.forall(x => x.isEmpty) then return None
    l.reduce((acc, elem) => {
      if elem.isEmpty then acc
      else Option(acc.get + elem.get)
    })   
}
  val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
  println(sumOpts(lista))
  println(sumOpts(List()))
  println(sumOpts(List(None, None)))
}


@main
def zad2: Unit = {
  def position[A](l: List[A], el: A): Option[Int] = {
    if (l.indexOf(el) != -1) return Option(l.indexOf(el))
    else return None
  }

  val lista = List(2, 1, 1, 5)
  println(position(lista, 1))
  println(position(lista, 3))
}

@main
def zad3: Unit = {
  def indices[A](l: List[A], el: A): Set[Int] = {
    l.zipWithIndex.filter(elem => elem(0) == el).map(elem => elem(1)).toSet
  }

  val lista = List(1, 2, 1, 1, 5)
  println(indices(lista, 1))
  println(indices(lista, 7))
}

@main
def zad4: Unit = {
  def swap[A](l: List[A]): List[A] = {
    val groupBy = l.groupBy(element => l.indexOf(element)%2==0)

    val parzyste=groupBy(false).map(List(_))
    val nieparzyste=groupBy(true).map(List(_))

    parzyste.zipAll(nieparzyste, Nil, Nil).flatMap(Function.tupled(_ ::: _))

  }

  val lista = List(1, 2, 3, 4, 5)
  println(swap(lista))
}

@main
def zad5: Unit = {
  val strefy: List[String] = java.util.TimeZone.getAvailableIDs.toList

  println(strefy.filter(timezone => timezone.slice(0,6) == "Europe").sortWith(_.stripPrefix("Europe/").length < _.stripPrefix("Europe/").length))
}

@main
def zad6: Unit = {
  def freq[A](l: List[A]): List[(A, Int)] = {
    l.groupBy(element => element).map(element => (element.head, element(1).length)).toList
  }

  val lista = List('a','b','a','c','c','a')
  println(freq(lista))
}