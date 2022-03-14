object zad3 extends App {

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