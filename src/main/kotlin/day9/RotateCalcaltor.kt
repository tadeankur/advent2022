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


fun akkumlerePostion(
    step: String,
    acc: MutableList<Pair<Int, Int>>,
    tail: MutableList<Pair<Int, Int>>
) {
    println(step)
    val stepsNo = step.split(" ").last().toInt()
    when (step.split(" ").first()) {
        "R" -> {
            for (i in 1..stepsNo step 1) {
                val previouStep = acc.last()
                val localHead = Pair(previouStep.first, previouStep.second.plus(1))
                val localTail = Pair(localHead.first, localHead.second.minus(1))
                acc.add(localHead)
                println("localHead:$localHead, localTail:$localTail, previouStep:${tail.last()}")

                if (isTailInTheSameRow(localHead, tail.last())) {
                    tail.add(localTail)
                }

                if (commonHoppingLogic(localHead, tail.last())
                ) {
                    tail.add(localTail)
                }
            }
        }

        "L" -> {
            for (i in 1..stepsNo step 1) {
                val previouStep = acc.last()
                val localHead = Pair(previouStep.first, previouStep.second.minus(1))
                val localTail = Pair(localHead.first, localHead.second.plus(1))
                println("localHead:$localHead, localTail:$localTail, previouStep:${tail.last()}")
                acc.add(localHead)

                if (isTailInTheSameRow(localHead, tail.last())) {
                    tail.add(localTail)
                }

                if (commonHoppingLogic(localHead, tail.last())
                ) {
                    tail.add(localTail)
                }
            }
        }

        "U" -> {
            for (i in 1..stepsNo step 1) {
                val previouStep = acc.last()
                val localHead = Pair(previouStep.first.plus(1), previouStep.second)
                val localTail = Pair(localHead.first.minus(1), previouStep.second)
                println("localHead:$localHead, localTail:$localTail, previouStep:${tail.last()}")
                acc.add(localHead)

                if (isTailOnTheSameColumn(localHead, tail.last())) {
                    tail.add(localTail)
                }

                if (commonHoppingLogic(localHead, tail.last())
                ) {
                    tail.add(localTail)
                }
            }

        }

        "D" -> {
            for (i in 1..stepsNo step 1) {
                val previouStep = acc.last()
                val localHead = Pair(previouStep.first.minus(1), previouStep.second)
                val localTail = Pair(localHead.first.plus(1), localHead.second)
                println("localHead:$localHead, localTail:$localTail, previouStep:${tail.last()}")
                acc.add(localHead)

                if (isTailOnTheSameColumn(localHead, tail.last())) {
                    tail.add(localTail)
                }

                if (commonHoppingLogic(localHead, tail.last())
                ) {
                    tail.add(localTail)
                }
            }
        }

        else -> {
            println("Ignore")
        }
    }
}

fun main() {
    val traversing = readInput("day9/userinput.txt")

    /*    val traversing = listOf<String>(
            "R 4", "U 4", "L 3", "D 1", "R 4", "D 1","L 5", "R 2"
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

