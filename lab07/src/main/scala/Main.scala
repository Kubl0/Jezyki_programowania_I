
@main
def zad1(): Unit = {
  val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
  println(sumOpts(lista))
}

@main
def zad2(): Unit = {
  val lista=List(2,1,1,5)
  println(position(lista,5))
}

def sumOpts(l: List[Option[Double]]): Option[Double] = {
    l.foldLeft(None: Option[Double])((accumulator: Option[Double], current:Option[Double]) =>

      (accumulator, current) match{
        case (None, Some(x)) => Some(x) 
        case (Some(x), None) => Some(x)
        case (Some(x), Some(y)) => Some(x+y)
        case (None, None) => None
      }

    )

}

def position[A](l: List[A], el: A): Option[Int] = {
    l.foldLeft(None: Option[Int]) ((acc: Option[Int], curr: A) =>
      (acc, curr) match{
        case (None, None) => None
        case (None, el) => Some(2)
        case (Some(_),_) => None
      }
    )

}



