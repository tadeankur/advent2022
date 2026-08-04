package no.bspoke.pam.konsument.day6

import no.bspoke.pam.konsument.day2.readInput
import kotlin.collections.mutableListOf

fun main() {

    val message = readInput("day6/userinput.txt").first()
    //val message = "bvwbjplbgvbhsrlpgdmjqwftvncz"

    message.toCharArray().foldIndexed(mutableListOf<Char>()) { index, acc, c ->

        println("$index  and  $acc and character $c")

        //Answer for Part1
       // if (acc.isEmpty() || acc.size <= 3) {

            //Answer for Part2
        if (acc.isEmpty() || acc.size <= 13) {
            acc += c
        } else {
            acc.removeFirst()
            acc += c
            println("before check for set $acc")
            //Answer for Part1
            //if (acc.toSet().size == 4) {
               //Answer for Part2
            if (acc.toSet().size == 14) {
                println("***Found the answer ${index + 1}")
                return
            }
        }

        acc
    }

}