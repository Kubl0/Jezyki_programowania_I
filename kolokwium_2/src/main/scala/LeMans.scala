import akka.actor.*
import scala.concurrent.duration.*
/*
  W konfiguracji projektu wykorzystana została wtyczka
  sbt-revolver. W związku z tym uruchamiamy program poleceniem

    reStart

  a zatrzymujemy pisząc (mimo przesuwających się komunikatów)

     reStop

  i naciskając klawisz ENTER. Jeśli czynności powyższe
  już wykonywaliśmy to możemy też przywołać poprzednie
  polecenia używając strzałek góra/dół na klawiaturze.
*/


@main
def wyścig: Unit = {
  val system = ActorSystem("LeMans")
  import system.dispatcher

  val MaksCyk = 100 // liczba „cyknięć” do wykonania (można eksperymentować)

  // UWAGA: „nazwy”, tworzące ścieżkę do aktora muszą być zapisywane
  // z użyciem znaków ASCII (a nie np. UTF8)!
  val organizator = system.actorOf(Props[Organizator](), "organizator")
  organizator ! Organizator.UstawMaksCyk(MaksCyk)
  
  organizator ! Organizator.Init(10)

  // Do „animacji” Organizatora wykorzystamy „Planistę” (Scheduler)
  val pantaRhei = system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,      // opóźnienie początkowe
    10.milliseconds,    // rzeczywisty odstęp pomiędzy kolejnymi „cyknięciami”
    organizator,        // adresat „korespondencji”
    Organizator.Cyk     // komunikat
  )

}
