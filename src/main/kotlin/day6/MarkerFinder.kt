package no.bspoke.pam.konsument.day6

import no.bspoke.pam.konsument.day2.readInput

private fun findMarkerEnd(
    message: String,
    markerSize: Int,
): Int = message.windowed(markerSize).indexOfFirst {
    println(it)
    it.toSet().size == markerSize
} + markerSize

private fun part1(message: String) {
    println(findMarkerEnd(message, 4))
}

private fun part2(message: String) {
    println(findMarkerEnd(message, 14))
}

fun main() {
    //val message = readInput("day6/userinput.txt").first()
    val message = "bvwbjplbgvbhsrlpgdmjqwftvncz"
    part1(message)
    part2(message)
}
