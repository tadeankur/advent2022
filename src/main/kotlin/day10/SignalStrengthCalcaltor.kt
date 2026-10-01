package no.bspoke.pam.konsument.day10

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.mutableListOf
import kotlin.math.abs


fun akkumlereCycles(
    step: String,
    acc: MutableList<Pair<Int, Int>>,
    lstPrint: MutableList<MutableList<String>>
) {
    println(step)
    if (acc.last().first % 40 == 0)
        lstPrint.add(mutableListOf<String>())

    val col = (acc.last().first - 1) % 40

    println("col = $col")

    when (step.split(" ").first()) {
        "noop" -> {
            val register = acc.last().second
            acc.add(Pair(acc.last().first + 1, register))
            val temp1 = acc.last().first + 1
            if (abs( temp1 % 40 - (register )) <= 1)
                lstPrint.last().add("#")
            else
                lstPrint.last().add(".")
        }

        "addx" -> {
            val stepsNo = step.split(" ").last().toInt()
            val register = acc.last().second
            val temp = acc.last().first + 1
            acc.add(Pair( temp, register))
            if (abs( temp % 40 - (register + stepsNo)) <= 1)
                lstPrint.last().add("#")
            else
                lstPrint.last().add(".")

            val temp1 = acc.last().first + 1
            acc.add(Pair( temp1, register + stepsNo))
            if (abs( temp1 % 40 - (register + stepsNo)) <= 1)
                lstPrint.last().add("#")
            else
                lstPrint.last().add(".")
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

    val screen = List(6) { StringBuilder() }
    var register = 1
    var cycle = 0

    fun tick() {
        val col = cycle % 40
        val row = cycle / 40
        screen[row].append(if ((col - register) in -1..1) '#' else ' ')
        cycle++
    }

    for (line in traversing) {
        if (line == "noop") {
            tick()
        } else {                     // "addx N"
            tick(); tick()
            register += line.substringAfter(" ").toInt()
        }
    }

    screen.forEach(::println)
    //  .filter { pair -> indexer.contains(pair.first) }
    // .fold(0) { acc, pair -> acc + pair.first * pair.second }.let { println(it) }
}

