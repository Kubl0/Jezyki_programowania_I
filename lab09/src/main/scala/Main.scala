@main
def zad1: Unit = {
  val lines = io.Source.fromFile("src/main/scala/resources/nazwiska.txt").getLines.toList

  val maxName = lines.foldLeft(0)((acc, fullName) => {
    val name=fullName.split(" +").head
    val max=name.groupBy(letter => letter.toLower).map(element => (element(0), element(1).length)).toList.length
    if (max > acc) max
    else acc
  })

  val minSurname = lines.foldLeft(Int.MaxValue)((acc, fullName) => {
    val surname=(fullName.split(" "))(1).length
    if(surname<acc) surname
    else acc
  })

  val listOfNames = lines.foldLeft(Nil: List[String])((acc, fullName) => {
    val name=fullName.split(" +").head
    if ((name.groupBy(letter => letter.toLower).map(element => (element(0), element(1).length)).toList.length)==maxName) acc:+fullName
    else acc
  })

  println(listOfNames.sortBy(elem => (elem.split(" +").last.length)))
}
