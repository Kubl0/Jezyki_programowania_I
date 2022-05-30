@main
def zad1: Unit = {
  def ocena(secret: List[Int], guess: List[Int]): (Int, Int) = {

    val black = secret.zip(guess).foldLeft(0)((acc, element) => {
      if (element(0)==element(1)) acc + 1
      else acc
    })

    val secretMap = secret.groupBy(element => element)
    val guessMap = guess.groupBy(element => element)

    println(secretMap)
    println(guessMap)

    val white = guessMap.map(element => {
    secretMap.getOrElse(element(0), List()).length
    }).sum - black

    (black,white)
  }

  val secret = List(1, 3, 2, 2, 4, 5)
  val guess  = List(2, 3, 2, 4, 1, 2)

  println(ocena(secret, guess))
}

@main
def zad2: Unit = {
  class Competitor(val name: String, val charmScore: List[Int] = Nil, val cunningScore: List[Int] = Nil){
    def getAverageCharmScore: Double = {
      charmScore.sum/charmScore.length
    }
  
    def getAverageCunningScore: Double = {
      cunningScore.sum/cunningScore.length
    }
  }

  def getResults(competitors: List[Competitor]): List[Competitor] = {
    competitors.sortBy(competitor => competitor.getAverageCharmScore).sortBy(competitor => competitor.getAverageCunningScore + competitor.getAverageCharmScore)
  }

  val competitor1 = new Competitor("Jan Kowalski", List(15, 8, 6, 20), List(12, 20, 11, 5))
  val competitor2 = new Competitor("Zofia Wieniawa", List(10, 8, 3, 16), List(19, 11, 13, 5))
  val competitor3 = new Competitor("Przemysław Okoń", List(13, 14, 13, 15, 16), List(20, 18, 15))

  val competitorsList: List[Competitor] = List(competitor1, competitor2, competitor3)

  getResults(competitorsList).foreach(element => println(element.name))
}

@main
def zad3: Unit = {
  def threeNumbers(n: Int): Seq[(Int, Int, Int)] = {
    for {
    c <- (1 to n).toList
    b <- 1 until c
    a <- 1 until b
    if c*c==a*a+b*b
  } yield(a,b,c)
}
  println(threeNumbers(12))
}
