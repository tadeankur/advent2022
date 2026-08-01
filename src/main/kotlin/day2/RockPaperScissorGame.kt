package no.bspoke.pam.konsument.day2

import java.io.File


fun readInput(name: String) = File("/Users/ankurtade/projects/advent2022/src/main/kotlin/$name").readLines()

//Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.

const val gameWinningPoints = 6
const val gameDrawPoints = 3

                        //R       P          S        R         P         S
val mapForScore = mapOf("A" to 1, "B" to 2, "C" to 3, "X" to 1, "Y" to 2, "Z" to 3)
                        //R       P          S

fun myScoreRound1(pair: Pair<String,String>) :Int {

    when (pair.first) {
        //Rock
        "A" -> pair.second.let {
            when(it) {
                "X" -> return 1.plus(gameDrawPoints) //Rock Rock Draw
                "Y" -> return 2.plus(gameWinningPoints) // Paper wins rock. I won the game
                "Z" -> return 3 // Rock wins Scissor. I lost the game
            }
        }
        //Paper
        "B" -> pair.second.let {
            when(it) {
                "X" -> return 1 //Paper defeats Rock
                "Y" -> return 2.plus(gameDrawPoints) // Paper paper match
                "Z" -> return 3.plus(gameWinningPoints) // scissor wins paper. I won the game
            }
        }

        //Scissor
        "C" -> pair.second.let {
            when(it) {
                "X" -> return 1.plus(gameWinningPoints)//Rock defeats Scissor. I won the game
                "Y" -> return 2// Paper loses Scissor
                "Z" -> return 3.plus(gameDrawPoints) // Scissor scissor match
            }
        }
    }
    return TODO("Provide the return value")
}


//X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win.
fun myScoreRound2(pair: Pair<String,String>) :Int {

    when (pair.first) {
        //Rock
        "A" -> pair.second.let {
            when(it) {
                "X" -> return 3 // Rock wins Scissor. I lost the game
                "Y" -> return 1.plus(gameDrawPoints)
                "Z" -> return 2.plus(gameWinningPoints) // Paper wins rock. I won the game
            }
        }
        //Paper
        "B" -> pair.second.let {
            when(it) {
                "X" -> return 1 //Paper defeats Rock
                "Y" -> return 2.plus(gameDrawPoints) // Paper paper match
                "Z" -> return 3.plus(gameWinningPoints) // scissor wins paper. I won the game
            }
        }

        //Scissor
        "C" -> pair.second.let {
            when(it) {
                "X" ->  return 2// Paper loses Scissor
                "Y" ->  return 3.plus(gameDrawPoints) // Scissor scissor match
                "Z" ->  return 1.plus(gameWinningPoints)//Rock defeats Scissor. I won the game
            }
        }
    }
    return TODO("Provide the return value")
}


fun main() {
    val input = readInput("day2/userinput.txt")
     val totalScoreRound1 = input.fold(0) {  acc, string ->
        val split: List<String> = string.split(" ")
        val pair = Pair(split.first(), second = split.last())
        acc + myScoreRound1(pair)
    }
    println(totalScoreRound1)
    val totalScoreRound2 = input.fold(0) { acc, string ->
        val split: List<String> = string.split(" ")
        val pair = Pair(split.first(), second = split.last())
        acc + myScoreRound2(pair)
    }

    println(totalScoreRound2)
}