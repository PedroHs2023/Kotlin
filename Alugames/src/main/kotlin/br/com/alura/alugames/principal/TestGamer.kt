import br.com.alura.alugames.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Jacque", "jacque@gmail.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Pedro",
        "pedrogamer2015@gmail.com"
         )
    println(gamer2)

    gamer1.let{
        it.dataNascimento = "19/05/2000"
        it.usuario = "Coelhinha"
    }.also{
        println(gamer1.idInterno )
    }
    gamer1.usuario = "pedrro"
    println(gamer1)
}