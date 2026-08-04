package no.bspoke.pam.konsument.day5

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.foldRightIndexed
import kotlin.collections.mutableMapOf

val regEx = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

fun main() {

    val input = readInput("day5/userinput.txt")

    val stack = input.fold(Pair(mutableListOf<List<String>>(), mutableListOf<String>())) { acc, line ->
        if (line.isNotEmpty() and !line.startsWith("move"))
            acc.first.add(line.chunked(4))
        else if (line.isNotEmpty() and line.startsWith("move"))
            acc.second.add(line)
        acc
    }

    val result = stack.first.foldRightIndexed(mutableMapOf<String, MutableList<String>?>()) { index, strings, acc ->
        if (acc.isEmpty())
            strings.map { string -> acc.put(string.trim(), mutableListOf()) }
        else {
            strings.mapIndexed { index, string ->
                if (string.isNotBlank() && string.isNotEmpty()) {
                    acc.get(index.plus(1).toString())?.add(string)
                }
            }
        }
        acc
    }

    stack.second.forEach {
        println(it)
        val matchResult = regEx.find(it)
        if (matchResult != null) {
            // Destructure the captured groups directly
            val (crateNo, from, to) = matchResult.destructured

            var fromList = result.get(from)
            var toList = result.get(to)
            val crateNoInt = crateNo.trim().toInt()

            //Solution for Part1
            // fromList?.takeLast(crateNoInt)?.toMutableList()?.let { elements -> toList?.addAll(elements.reversed()) }
            //Solution for Part2
             fromList?.takeLast(crateNoInt)?.toMutableList()?.let { elements -> toList?.addAll(elements) }


            result[from] = fromList?.dropLast(crateNoInt)?.toMutableList()


        }
    }

    result.forEach { string, strings -> print(strings?.last()?.filter { it.isLetter() })}
}