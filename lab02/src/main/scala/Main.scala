
def obramuj(napis: String): String = {
  val array=napis.split("/n")
}

@main
def zad1: Unit = {
  
  var argument = """
      Ala
      ma
      kota i psa
  """

  var wynik = obramuj(argument)
  
  println(wynik)


}