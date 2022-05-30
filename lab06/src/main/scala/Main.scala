@main
def zad1: Unit = {
  def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
    list.drop(begIdx).take(endIdx-begIdx+1)
}
  println(subseq(List(1,2,3,4,5,6,7,8,9,10,11), 2, 7))
}

@main 
def zad2: Unit ={
  def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
    val lista=list.filter(_!=0.0)
    val (list1, _) = lista.partition(element => element > 0.0)
    val (_, list2) = lista.partition(element => element > 0.0)

    return (list1,list2)
}

  println(pairPosNeg(List(0.1,1.5,2.3,-1.0,0.0,-2.1)))
}

@main
def zad3: Unit ={
  def isOrdered[A](seq: Seq[A])(leq:(A, A) => Boolean): Boolean = {
  seq.sliding(2).toList.foldLeft(true)((acc, element) =>
    if (leq(element.head, element(1))) acc
    else false
  )
}
  val seq = Seq(1, 2, 2, 4)
  println(isOrdered(seq)(_ < _))
  println(isOrdered(seq)(_ <= _))
}


@main
def zad4: Unit={
  def deStutter[A](list: List[A]): List[A] = {
    list.foldLeft(List(): List[A])((acc, element) =>
    if (acc.indexOf(element) != -1) acc
    else acc:+element
    )
}
  val l = List(1, 1, 2, 4, 4, 4, 1, 3)
  println(deStutter(l))
}


@main
def zad5: Unit ={
  def remElems[A](list: List[A], k: Int): List[A] = {
    list.zipWithIndex.filter(element => element(1)!=k).map(element => element(0))
} 

  println(remElems(List(1,2,3,4,5,6,7,8,9,10), 5))
}

@main
def zad6: Unit ={
  def freqMax[A](list: List[A]) = {
    val uniques = list.toSet
    val uniquesWithCounters = uniques.map(element => (element, list.count(num => num == element)))
    val max = uniquesWithCounters.maxBy((_, howMuch) => howMuch).toList(1)

    (uniquesWithCounters.filter((_, counter) => counter == max).map((number, _) => number), max)

}
  println(freqMax(List(1, 1, 2, 4, 4, 3, 4, 1, 3)))
}