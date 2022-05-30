
def frame(script: String): String = {
  val lines = script.split("\n")
  val longestLine = lines.maxBy(s => s.length).length

  f"${"*" * (longestLine + 4)}" +
    "\n" +
    "*"+" "*(longestLine+2)+
    lines.map(line => {
      (f"*$line")
    }).mkString("\n")+
    "\n" +
    "*"+" "*(longestLine+2)+"*\n"+
    f"${"*" * (longestLine + 4)}"
}

@main
def zad1: Unit = {
  var argument = 
    """
      |Ala 
      |ma 
      |kota i psa""".stripMargin


  var wynik=frame(argument)

  println(wynik)

}