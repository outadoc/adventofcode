package fr.outadoc.aoc.twentytwenty

import fr.outadoc.aoc.scaffold.Day
import fr.outadoc.aoc.scaffold.readDayInput

class Day25 : Day<Long> {

    companion object {
        private const val IV = 20201227
    }

    private val input = readDayInput().lines()

    private val cardPublicKey: Long = input[0].toLong()
    private val doorPublicKey: Long = input[1].toLong()

    private fun singleLoop(acc: Long, subject: Long): Long {
        return (acc * subject) % IV
    }

    private fun transform(subject: Long, loopSize: Int): Long {
        return (0 until loopSize).fold(1L) { acc, _ ->
            singleLoop(acc, subject)
        }
    }

    private fun findLoopSizeForPublicKey(subject: Long, publicKey: Long): Int {
        var acc = 1L
        var loopSize = 1
        while (acc != publicKey) {
            acc = singleLoop(acc, subject)
            loopSize++
        }
        return loopSize - 1
    }

    override fun step1(): Long {
        val cardLoopSize = findLoopSizeForPublicKey(subject = 7, publicKey = cardPublicKey)
        return transform(subject = doorPublicKey, loopSize = cardLoopSize)
    }

    override fun step2(): Long = 0

    override val expectedStep1: Long = 6198540
    override val expectedStep2: Long = 0
}