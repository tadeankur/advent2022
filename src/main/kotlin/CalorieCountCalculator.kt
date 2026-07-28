package no.bspoke.pam.konsument

import java.io.File
import kotlin.collections.mutableMapOf

val mapElvesWithCalories = mutableMapOf<String, List<Int>>(
    ("Elves1" to mutableListOf<Int>(
        1000,
        2000,
        3000
    )),
    "Elves1" to mutableListOf(4000),
    "Elves2" to mutableListOf(5000, 6000),
    "Elves3" to mutableListOf(7000, 8000, 9000),
    "Elves4" to mutableListOf(10000)
)

fun calcLstElvesWithHigeshestCalories(mapWithCalories: Map<String, List<Int>>) =
    mapWithCalories.entries.maxByOrNull { it.value.sum() }

fun readInputFileFromURL(): MutableMap<String, MutableList<Int>> {
    val lines: List<String> = File("/Users/ankurtade/projects/Advent2025/src/main/resources/userinput.txt").readLines()

    return lines.foldIndexed(mutableMapOf()) { index, acc, line ->
        if (acc.isEmpty() || line.isBlank()) {
            acc.put("Elves${index + 1}", mutableListOf())
        } else {
            acc.entries.last().value.add(line.toInt())
        }
        acc
    }
}

fun main() {
    readInputFileFromURL()
    val startTime = System.currentTimeMillis()
    println(calcLstElvesWithHigeshestCalories(readInputFileFromURL())?.value?.sum())
    val endTime = System.currentTimeMillis()
    println("time taken to sort -> ${endTime - startTime}")

}