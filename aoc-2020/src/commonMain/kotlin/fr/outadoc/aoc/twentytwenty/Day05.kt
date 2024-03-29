package fr.outadoc.aoc.twentytwenty

import fr.outadoc.aoc.scaffold.Day
import fr.outadoc.aoc.scaffold.readDayInput
import kotlin.math.pow

class Day05 : Day<Int> {

    private val registeredSeats: List<Seat> =
        readDayInput()
            .lineSequence()
            .map { it.parseSeat() }
            .toList()

    companion object {
        const val ROW_CHAR_COUNT = 7
        const val COL_CHAR_COUNT = 3

        const val FRONT = 'F'
        const val BACK = 'B'
        const val RIGHT = 'R'
        const val LEFT = 'L'

        val rowCount = 2f.pow(ROW_CHAR_COUNT).toInt()
        val columnCount = 2f.pow(COL_CHAR_COUNT).toInt()
    }

    data class Seat(val row: Int, val col: Int, val id: Int = row * 8 + col)

    private fun String.parseSeat(): Seat {
        val rowCode = take(ROW_CHAR_COUNT)
        val colCode = takeLast(COL_CHAR_COUNT)
        return Seat(
            row = getPositionFromCode(rowCode, min = 0, max = rowCount - 1),
            col = getPositionFromCode(colCode, min = 0, max = columnCount - 1)
        )
    }

    private tailrec fun getPositionFromCode(code: String, min: Int, max: Int): Int {
        // println("getPositionFromCode($code, $min, $max)")

        if (code.length == 1) {
            return when (code.first()) {
                // Lower half
                FRONT, LEFT -> min
                // Upper half
                BACK, RIGHT -> max
                else -> throw IllegalArgumentException()
            }
        }

        val (newMin, newMax) = when (code.first()) {
            // Lower half
            FRONT, LEFT -> min to min + ((max - min) / 2)
            // Upper half
            BACK, RIGHT -> min + ((max - min) / 2) + 1 to max
            else -> throw IllegalArgumentException()
        }

        return getPositionFromCode(code.drop(1), newMin, newMax)
    }

    override fun step1(): Int {
        return registeredSeats.maxOf { it.id }
    }

    override fun step2(): Int {
        val allSeats = (0 until rowCount).map { row ->
            (0 until columnCount).map { col ->
                Seat(row, col)
            }
        }.flatten()

        val minId = registeredSeats.minOf { it.id }
        val maxId = registeredSeats.maxOf { it.id }

        val notRegistered = allSeats.filterNot { seat ->
            seat in registeredSeats
        }.filter { seat ->
            seat.id in (minId..maxId)
        }

        return notRegistered.first().id
    }

    override val expectedStep1: Int = 915
    override val expectedStep2: Int = 699
}