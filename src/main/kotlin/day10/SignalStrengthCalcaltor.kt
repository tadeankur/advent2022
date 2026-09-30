package no.bspoke.pam.konsument.day10

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.mutableListOf

fun akkumlereCycles(
    step: String,
    acc: MutableList<Pair<Int, Int>>
) {
    println(step)


    when (step.split(" ").first()) {
        "noop" -> {
            acc.add(Pair(acc.last().first + 1, acc.last().second))
        }

        "addx" -> {
            val stepsNo = step.split(" ").last().toInt()
            acc.add(Pair(acc.last().first + 1, acc.last().second))
            acc.add(Pair(acc.last().first + 1, acc.last().second + stepsNo))
        }

        else -> {
            println("Ignore")
        }
    }
}

fun main() {
    val traversing = readInput("day10/userinput.txt")

    /*  val traversing = listOf<String>(
          "noop",
          "addx 3",
          "addx -5"
          //,"L 3", "D 1", "R 4"
      )*/
    //"D 1", "R 4", "D 1" , "L 5" , "R 2")
    //"U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2")

    val indexer = mutableListOf<Int>(20, 60, 100, 140, 180, 220)
    traversing
        .fold(mutableListOf(Pair(1, 1))) { acc, step ->
            akkumlereCycles(step, acc)
            acc
        }
        .filter { pair -> indexer.contains(pair.first) }
        .fold(0) { acc, pair -> acc + pair.first * pair.second }.let { println(it) }
}

