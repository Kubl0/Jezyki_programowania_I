
@main
def zad1: Unit = {
  print("Podaj liczbe >> ")
  val liczba = io.StdIn.readInt()
  if(liczba%2==0)
    println("Liczba jest parzysta")
  else
    println("Liczba jest nieparzysta")
}

@main
def zad2: Unit = {
  print("Podaj pierwsza liczbe >> ")
  var liczba1 = io.StdIn.readInt()
  print("Podaj druga liczbe >> ")
  var liczba2 = io.StdIn.readInt()

  while(liczba1!=liczba2){
    if(liczba1>liczba2)
      liczba1=liczba1-liczba2
    else
      liczba2=liczba2-liczba1
    }

    println(s"Najwiekszy wspolny dzielnik to: $liczba1")
}

@main
def zad3: Unit = {
  print("Podaj liczbe >> ")
  val liczba = io.StdIn.readInt()
  var a=liczba
  var test=0

  while(a>0){
    if(liczba%a==0)
      test=test+1
    a=a-1
    }
  if(test>2)
    println("Nie jest pierwsza")
  else
    println("Jest pierwsza")
}