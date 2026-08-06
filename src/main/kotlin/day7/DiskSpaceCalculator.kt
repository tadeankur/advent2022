package no.bspoke.pam.konsument.day7

import no.bspoke.pam.konsument.day2.readInput


fun main() {
    val files = readInput("day7/userinput.txt").filterNot { it.equals("$ ls") }
    var currentPath = ""
    var folderSize = 0;

    val result = files.fold(mutableMapOf<String, Int>()) { acc, path ->

        println("Reading $path")
        if (path.startsWith("$ cd")) {
            val secondPath = path.split(" ").last()
            acc[currentPath] = 0
            println("Reading $secondPath")

            if (secondPath.trim().equals("/")) {
                currentPath = "/"
            } else if (secondPath.trim().equals("..")) {
                currentPath = currentPath.substring(0, currentPath.lastIndexOf("/") + 1)

            } else {
                currentPath += "$secondPath/"
            }
        }

        if (path.split(" ").first().all { it.isDigit() }) {
            acc[currentPath + path.split(" ").last()] = path.split(" ").first().toInt()
        }
        acc
    }

    //constrainedSum = numbers.runningFold(0) { acc, value -> acc + value }

    // re    .takeWhile { it <= limit }
    result.entries.sortedBy { entry -> entry.key }.map { searchEntry ->
       // println("$searchEntry")
        result.filter { entry -> entry.key.contains(searchEntry.key) }
            .entries.fold(0) { acc, entry -> acc + entry.value }
            .takeIf {
                ( it <= 100000)
            }.let { println("$searchEntry: $it") }

    }
    //.filterNotNull().sumOf { it }.let { println(it) }

}