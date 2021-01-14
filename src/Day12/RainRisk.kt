package Day12

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.absoluteValue

fun main() {

    val input = Files.readAllLines(Paths.get("/home/zyw/dev/aoc/2020/src/Day12/input.txt"))

    val answer = moveShip(input)
    println("Part 1:$answer")

    val answerPart2 = moveShipPart2(input)
    println("Part 2: $answerPart2")
}

private fun moveShip(instructions: List<String>): Int {
    var x = 0
    var y = 0
    var facing = 0 // 0 = EAST, clockwise ++

    instructions.forEach { instr ->
        val action = instr[0]
        val i = instr.substring(1).toInt()
        when (action) {
            'N' -> y += i
            'S' -> y -= i
            'E' -> x += i
            'W' -> x -= i
            'L' -> facing = (360 + facing - i) % 360
            'R' -> facing = (facing + i) % 360
            'F' -> when (facing) {
                0 -> x += i
                90 -> y -= i
                180 -> x -= i
                270 -> y += i
                else -> error("Unknown degree found")
            }
        }
    }

    return x.absoluteValue + y.absoluteValue
}

private fun moveShipPart2(instructions: List<String>): Int {
    var x = 0
    var y = 0
    var waypointX = 10 // offset
    var waypointY = 1

    instructions.forEach { instr ->
        val action = instr[0]
        val i = instr.substring(1).toInt()
        when (action) {
            'N' -> waypointY += i
            'S' -> waypointY -= i
            'E' -> waypointX += i
            'W' -> waypointX -= i
            'R' -> when (i) {
                90 -> waypointY = -waypointX.also { waypointX = waypointY }
                180 -> { waypointX = -waypointX; waypointY = -waypointY }
                270 -> waypointY = waypointX.also { waypointX = -waypointY }
            }
            'L' -> when (i) {
                270 -> waypointY = -waypointX.also { waypointX = waypointY }
                180 -> { waypointX = -waypointX; waypointY = -waypointY }
                90 -> waypointY = waypointX.also { waypointX = -waypointY }
            }
            'F' -> {
                x += waypointX * i
                y += waypointY * i
            }
        }
    }

    return x.absoluteValue + y.absoluteValue
}