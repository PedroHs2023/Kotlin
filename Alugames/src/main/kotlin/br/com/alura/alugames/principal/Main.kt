package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.servicos.ConsumoApi
import org.example.br.com.alura.alugames.modelo.Jogo
import transformarEmIdade
import java.io.IO.println
import java.util.*


fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso. Dados do gamer:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite um código de um jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)


        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Jogo inexistente. Tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descricao personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)){
                println("Insira a descrivao personalizada para o jogo: ")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }
            gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? S/N")
        val resposta= leitura.nextLine()


    } while(resposta.equals("s", true))

    println("Jogos Buscados: ")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por Titulo: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach{
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter{
        it?.titulo?.contains("batman", true) ?: false
    }
    println("Jogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()

    if (opcao.equals("s",true)){
        println(gamer.jogosBuscados)
        println("\nInforme a posicao do jogo que deseja excluir")
        val posicao = leitura.nextInt()
       gamer.jogosBuscados.removeAt(posicao)
    }
    println("\n Lista atualizada: ")
    println(gamer.jogosBuscados)




    println("Busca finalizada com sucesso!")


}



