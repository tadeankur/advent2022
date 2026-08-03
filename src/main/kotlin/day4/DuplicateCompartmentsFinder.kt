package no.bspoke.pam.konsument.day4

import jdk.internal.net.http.common.Pair.pair
import no.bspoke.pam.konsument.day2.readInput


fun checkIfDuplicateRange(pair: Pair<String, String>): Int {
    val firstRange = pair.first.split("-").let { it[0].toInt()..it[1].toInt() }.toList()
    val secondRange = pair.second.split("-").let { it[0].toInt()..it[1].toInt() }.toList()

    return if (firstRange.size >= secondRange.size && firstRange.containsAll(secondRange))
        1
    else if (secondRange.size >= firstRange.size && secondRange.containsAll(firstRange))
        1
    else 0

}

fun checkIfOverLap(pair: Pair<String, String>): Int {
    val firstRange = pair.first.split("-").let { it[0].toInt()..it[1].toInt() }.toList()
    val secondRange = pair.second.split("-").let { it[0].toInt()..it[1].toInt() }.toList()
    return if (firstRange.toSet().intersect(secondRange.toSet()).isNotEmpty()) 1 else 0
}

private fun part1(input: List<String>) {
    input.fold(0) { acc, string ->
        val splittetString = string.split(",")
        val pair = Pair(splittetString.first(), splittetString.last())
        acc + checkIfDuplicateRange(pair)

    }.let { println(it) }
}

fun part2(input: List<String>) {
    input.fold(0) { acc, string ->
        val splittetString = string.split(",")
        val pair = Pair(splittetString.first(), splittetString.last())
        acc + checkIfOverLap(pair)

    }.let { println(it) }
}

fun main() {

    val input = readInput("day4/userinput.txt")

    part1(input)
    part2(input)


}

