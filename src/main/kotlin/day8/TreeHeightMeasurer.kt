package no.bspoke.pam.konsument.day8

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.maxByOrNull
import kotlin.collections.mutableListOf

fun zipColumnTreesForChrimas(treeIndex: Int, source: MutableList<List<Int>>) = source.map { it[treeIndex] }.toList()

fun part1() {
    val treeMatrix = readInput("day8/userinput.txt")

    val lstTM = treeMatrix.fold(mutableListOf<List<Int>>()) { acc, string ->
        acc += listOf(string.map { it.digitToInt() })
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

fun findFirstIndexOfSameOrMax(number: Int, lst: List<Int>): Int? {
    if (number > lst.maxOrNull()!!) return lst.size
    if (lst.indexOfFirst { it == number } == 0) return 1 else return lst.indexOfFirst { it == number || it > number } + 1
}

//part 2
fun main() {
    val treeMatrix = readInput("day8/userinput.txt")

    val lstTM = treeMatrix.fold(mutableListOf<List<Int>>()) { acc, string ->
        acc += listOf(string.map { it.digitToInt() })
        acc
    }


    lstTM.foldIndexed(mutableListOf<Int>()) { rowIndex, acc, numbers ->
        println("$rowIndex and $numbers")
        if (rowIndex != 0 && rowIndex != lstTM.lastIndex) {
            // compare current with first and last and see if it's bigger than that. If it's smaller or same size
            // then ignore it.
            // compare current with first and last and see if it's bigger than that. If it's smaller or same size
            // then ignore it.
            numbers.forEachIndexed() { index, number ->

                println("$index and $number")
                // find the max for the row
                //Ignore the  trees on edge for comparison
                val lstColumn = zipColumnTreesForChrimas(index, lstTM)

                if (index != 0 && index != numbers.lastIndex) {
                    acc.add(
                        findFirstIndexOfSameOrMax(number, numbers.take(index).reversed())!! //left
                                *
                                findFirstIndexOfSameOrMax(number, numbers.drop(index + 1))!! //right
                                *
                                findFirstIndexOfSameOrMax(number, lstColumn.take(rowIndex).reversed())!!  //top
                                *
                                findFirstIndexOfSameOrMax(number, lstColumn.drop(rowIndex + 1))!!  //bottom
                    )
                }
            }
        }
        acc
    }.let { println(it) }
}

