package no.bspoke.pam.konsument.day3

import no.bspoke.pam.konsument.day2.readInput

fun String.splitInMiddle(): Pair<String, String> {
    val mid = this.length / 2
    return Pair(this.substring(0, mid), this.substring(mid))
}

fun findCommonCharacters(input: Pair<String, String>) =
    input.first.toSet().intersect(input.second.toSet()).toCharArray()

fun findCommonCharactersForList(input: List<String>): CharArray {
    return input.reduce { acc, string ->
        String(findCommonCharacters(Pair(acc, string)))
    }.toCharArray()
}

//Part1
private fun findduplicatesinCompartment(input: List<String>) {
    val result = input.fold(0) { acc, string ->
        acc + findCommonCharacters(string.splitInMiddle()).sumOf {
            if (it.isLowerCase())
                it.code.minus(96)
            else it.code.minus(38)
        }
    }
    println(result)
}

//Part2
private fun findduplicatesInGroup(input: List<String>) {
    val result = input.chunked(3).fold(0) { acc, chunk ->
        acc + findCommonCharactersForList(chunk).sumOf {
            if (it.isLowerCase())
                it.code.minus(96)
            else it.code.minus(38)
        }
    }
    println(result)
}


fun main() {
    val input = readInput("day3/userinput.txt")
    findduplicatesinCompartment(input)
    findduplicatesInGroup(input)
}

