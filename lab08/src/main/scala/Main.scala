
@main
def zad3(): Unit ={
  val n=10
  println(threeNumbers(n))
}


def threeNumbers(n: Int): Seq[(Int, Int, Int)] = {
  for(a<-0 to n){
    for(b<-0 to n){
      for(c<-0 to n){
        if((a*a)+(b*b)==(c*c) && a<b){
          println(Seq(a,b,c))
        }
      }
    }
  }
}

