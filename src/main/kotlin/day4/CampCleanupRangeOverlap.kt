package no.bspoke.pam.konsument.day4

import no.bspoke.pam.konsument.day2.readInput

private fun String.toRange(): IntRange =
    split("-").let { (start, end) -> start.toInt()..end.toInt() }

private fun parseRangePair(line: String): Pair<IntRange, IntRange> {
    val (first, second) = line.split(",")
    return first.toRange() to second.toRange()
}

private fun isFullyContained(first: IntRange, second: IntRange): Boolean =
    (first.first <= second.first && first.last >= second.last) ||
        (second.first <= first.first && second.last >= first.last)

private fun hasOverlap(first: IntRange, second: IntRange): Boolean =
    first.first <= second.last && second.first <= first.last

private fun part1(input: List<String>) {
    val count = input.count { line ->
        val (first, second) = parseRangePair(line)
        isFullyContained(first, second)
    }
    println(count)
}

private fun part2(input: List<String>) {
    val count = input.count { line ->
        val (first, second) = parseRangePair(line)
        hasOverlap(first, second)
    }
    println(count)
}

fun main() {
    val input = readInput("day4/userinput.txt")
    part1(input)
    part2(input)
}
