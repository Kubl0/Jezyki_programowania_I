
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
  print("Podaj x >> ")
  var x = io.StdIn.readInt()
  print("Podaj y >> ")
  var y = io.StdIn.readInt()

  while(x!=y){
    if(x>y)
      x=x-y
    else
      y=y-x
  }

  println(s"Najwiekszy wspolny dzielnik to: $x")
}

@main
def zad3: Unit = {
  print("Podaj liczbe >> ")
  val liczba = io.StdIn.readInt()

  var check=0

  for(i <- 1 to liczba){
    if(liczba%i==0)
      check=check+1
  }

  if(check<=2)
    println("Jest pierwsza")
  else
    println("Nie jest pierwsza")
}

@main
def zad4: Unit = {
  print("Podaj liczbe >> ")
  val liczba = io.StdIn.readInt()
  var check=0
  var done=0
  if(liczba<2)
    println("Liczba musi byc wieksza od 2")
  else{

    def prime(liczba: Int): Int = {
      var check=0
        for(i <- 1 to liczba){
          if(liczba%i==0)
            check=check+1
        }

      if(check<=2)
        return 1
      else
        return 0
    }

    for(i <- 2 to liczba/2){
      if(prime(i)==1){
        if(prime(liczba-i)==1){
          print(i,liczba-i)
          done=1
        }
      }
    }

    if(done==0)
      println("Nie da sie")
  }
}
