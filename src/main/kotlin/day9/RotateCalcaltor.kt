package no.bspoke.pam.konsument.day9

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.mutableListOf
import kotlin.math.abs


fun isTailInTheSameRow(head: Pair<Int, Int>, tail: Pair<Int, Int>) =
    head.first - tail.first == 0 && abs(head.second - tail.second) == 2

fun isTailOnTheSameColumn(head: Pair<Int, Int>, tail: Pair<Int, Int>) =
    abs(head.first - tail.first) == 2 && head.second - tail.second == 0


fun commonHoppingLogic(head: Pair<Int, Int>, previous: Pair<Int, Int>) =
    (abs(head.first - previous.first) == 2 && abs(head.second - previous.second) == 1) ||
            (abs(head.first - previous.first) == 1 && abs(head.second - previous.second) == 2)


var localHead = Pair(0, 0)
var localTail = Pair(0, 0)

fun akkumlerePostion(
    step: String,
    acc: MutableList<Pair<Int, Int>>,
    tail: MutableList<Pair<Int, Int>>
) {
    println(step)
    val stepsNo = step.split(" ").last().toInt()


    for (i in 1..stepsNo step 1) {
        val previouStep = acc.last()

        when (step.split(" ").first()) {
            "R" -> {
                localHead = Pair(previouStep.first, previouStep.second.plus(1))
                localTail = Pair(localHead.first, localHead.second.minus(1))
                if (isTailInTheSameRow(localHead, tail.last())) {
                    tail.add(localTail)
                }
            }

            "L" -> {
                localHead = Pair(previouStep.first, previouStep.second.minus(1))
                localTail = Pair(localHead.first, localHead.second.plus(1))
                if (isTailInTheSameRow(localHead, tail.last())) {
                    tail.add(localTail)
                }
            }

            "U" -> {
                localHead = Pair(previouStep.first.plus(1), previouStep.second)
                localTail = Pair(localHead.first.minus(1), previouStep.second)

                if (isTailOnTheSameColumn(localHead, tail.last())) {
                    tail.add(localTail)
                }
            }

            "D" -> {
                localHead = Pair(previouStep.first.minus(1), previouStep.second)
                localTail = Pair(localHead.first.plus(1), localHead.second)

                if (isTailOnTheSameColumn(localHead, tail.last())) {
                    tail.add(localTail)
                }
            }

            else -> {
                println("Ignore")
            }
        }
        acc.add(localHead)
        println("localHead:$localHead, localTail:$localTail, previouStep:${tail.last()}")



        if (commonHoppingLogic(localHead, tail.last())
        ) {
            tail.add(localTail)
        }
    }
}

fun main() {
     val traversing = readInput("day9/userinput.txt")

    /*val traversing = listOf<String>(
        "R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2"
        //,"L 3", "D 1", "R 4"
    )*/
    //"D 1", "R 4", "D 1" , "L 5" , "R 2")
    //"U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2")

    val tailMovement = mutableListOf<Pair<Int, Int>>(Pair(1, 1))

    traversing
        .fold(mutableListOf(Pair(1, 1))) { acc, step ->
            akkumlerePostion(step, acc, tailMovement)
            acc
        }.let { println(it) }

    println("tailMovement = $tailMovement");
    println(tailMovement.toSet().size)
}

