package org.example.br.com.alura.alugames.modelo


data class Jogo(val titulo:String,
           val capa:String) {
    var descricao:String?   = null

    override fun toString(): String {
        return "\n Meu Jogo:\n" +
                "titulo: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao"
    }
}