/*
  UWAGA! Uzupełnij kod funkcji „ranking” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def ranking(): List[(Int, Int)] = {
  val input = io.Source
    .fromResource("test.txt")
    .getLines
    .toList

  val result = input.drop(1).map(el => el.split(" ").drop(1).toList)
  val result2 = result.map(el => el.zipWithIndex).flatten.toList
  val result3 = result2.map(el => (el(1)+1, el(0)))

  val result4 = result3.groupBy(_(0)).toList.map(el => (el(0), el(1).map( nest => nest(1))))

  val result5 = result4.map(el => (el(0), ((el(1).map(el => el.toDouble)).sum/el(1).length).toDouble))

  val result6 = result5.filter(_(1)>0.5).sortBy(_(1)).reverse

  val result7 = result6.map(el => (el(0)))
  val result8 = result7.zipWithIndex
  val result9 = result8.map( el => (el(1)+1, el(0)))
  
  result9

}

def zadanie_1: Unit = {
  val msg = """
  Nie ma potrzeby definiowania ani uruchamiania funkcji @main.

    Zamiast polecenia „run” możesz wykorzystać polecenie „test”, które
    sprawdzi poprawność Twoich rozwiązań i zgłosi ewentualne problemy.

  Oczywiście jeśli bardzo chcesz to możesz użyć funkcji @main
  do „ręcznego” sprawdzania swoich rozwiązań.
  """
  println(msg)
}

@main
def zad01: Unit = {
  println(ranking())
}

