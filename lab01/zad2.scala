object zad2 extends App {

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

