package no.bspoke.pam.konsument.day8

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.mutableListOf

fun zipColumnTreesForChrimas(treeIndex: Int, source: MutableList<List<Int>>) = source.map { it[treeIndex] }.toList()

fun main() {
    val treeMatrix = readInput("day8/userinput.txt")

    val lstTM = treeMatrix.fold(mutableListOf<List<Int>>()) { acc, string ->
        acc.addAll(listOf(string.toCharArray().map { it.digitToInt() }))
        acc
    }

    lstTM.foldIndexed(mutableListOf<Int>()) { rowIndex, acc, numbers ->
        println("$rowIndex and $numbers")
        if (rowIndex == 0 || rowIndex == lstTM.lastIndex) {
            acc.addAll(numbers)
        } else {
            acc.add(numbers.first())
            acc.add(numbers.last())
            // compare current with first and last and see if it's bigger than that. If it's smaller or same size
            // then ignore it.
            numbers.forEachIndexed() { index, number ->
                // find the max for the row
                //Ignore the  trees on edge for comparison
                val maxWithIndex = numbers.withIndex().maxByOrNull { it.value }!!

                val lstColumn = zipColumnTreesForChrimas(index, lstTM)

                if (index != 0 && index != numbers.lastIndex) {
                    println("$index")
                    if (number > numbers.take(index).max()
                        || number > numbers.drop(index + 1).max()
                        || number > lstColumn.take(rowIndex).max()
                        || number > lstColumn.drop(rowIndex + 1).max()
                    ) {
                        acc.add(number) //visible from left of the row
                        return@forEachIndexed
                    }
                }
            }
        }
        acc
    }.let { println(it.size) }
}

