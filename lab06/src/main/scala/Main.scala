
@main
def zad1() : Unit = {
  println(subseq(List(0,1,2,3,4,5,6), 3, 5))
}


@main
def zad2() : Unit = {
  println(pairPosNeg(List(-1.2, -0.7, -0.1, 1, 2, 3.1, 2.5, 1.7, -5.3)))
}

@main
def zad3() : Unit = {
  val seq = Seq(1,2,3,4)

  println(isOrdered(seq)(_ < _))

}

def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
  list.take(endIdx+1).drop(begIdx)
}

def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
  list.filter(_!=0.0).partition(_ < 0.0)
}


def isOrdered[A](seq: Seq[A])(leq:(A, A) => Boolean): Boolean = {
  seq.sliding(2,1).filter()
}



