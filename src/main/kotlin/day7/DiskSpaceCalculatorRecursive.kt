package no.bspoke.pam.konsument.day7

import no.bspoke.pam.konsument.day2.readInput

class Directory(
    var name: String,
    var parent: Directory? = null
) {
    var directories = mutableListOf<Directory>()
    var files = mutableListOf<File>()
}

data class File(
    var name: String,
    var size: Int
)

fun calculateSize(
    dir: Directory,
    directorySizes: MutableList<Int>
): Int {

    // Sum of files directly inside this directory
    var size = dir.files.sumOf { it.size }

    // Recursively calculate child directory sizes
    for (child in dir.directories) {
        size += calculateSize(child, directorySizes)
    }

    // Remember this directory's total size
    directorySizes.add(size)

    return size
}

fun main() {
    val files = readInput("day7/userinput.txt").filterNot { it.equals("$ ls") }
    val rootDirectory = Directory("/", null)
    var currentDirectory = rootDirectory
    files.forEach() { path ->

        println("Reading $path")
        if (path.startsWith("$ cd")) {
            val internalPath = path.removePrefix("$ cd ")
            if (internalPath.trim().equals("..")) {
                currentDirectory = currentDirectory.parent!!

            } else if (internalPath.trim().equals("/")) {
                currentDirectory = rootDirectory
            } else {
                var newDirectory = Directory(internalPath, currentDirectory)
                currentDirectory.directories.add(newDirectory)
                currentDirectory = newDirectory
            }
        }

        if (path.split(" ").first().all { it.isDigit() }) {
            currentDirectory.files.add(
                File(
                    path.split(" ").last(),
                    path.split(" ").first().toInt()
                )
            )
        }
    }

    val allSizes = mutableListOf<Int>()

    calculateSize(rootDirectory, allSizes)

    //part1
    var answer = allSizes
        .filter { it <= 100000 }
        .sum()

    println(answer)

    println(allSizes.sum())

    //Part2
    val totalDisk = 70_000_000L
    val requiredUnused = 30_000_000L
    val currentUsed = calculateSize(rootDirectory, allSizes)
    val neededToFree = requiredUnused - (totalDisk - currentUsed)
    answer = allSizes
        .filter { it >= neededToFree }.min()
    println(answer)
}