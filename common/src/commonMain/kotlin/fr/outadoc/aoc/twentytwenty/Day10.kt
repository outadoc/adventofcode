package fr.outadoc.aoc.twentytwenty

import fr.outadoc.aoc.scaffold.Day
import fr.outadoc.aoc.scaffold.Year

class Day10 : Day(Year.TwentyTwenty) {

    companion object {
        const val OUTLET_JOLTS = 0L
        const val BUILT_IN_ADAPTER_JOLTS_DIFFERENCE = 3
        val ADAPTER_TOLERANCE = 1..3
    }

    private val input: List<Long> =
        readDayInput()
            .lines()
            .map { it.toLong() }

    private val buildInAdapterJolts = input.maxOrNull()!! + BUILT_IN_ADAPTER_JOLTS_DIFFERENCE

    private val adapterList: List<Long> =
        input

    private tailrec fun makeAdapterChain(
        remainingAdapters: List<Long>,
        currentChain: List<Long> = listOf(OUTLET_JOLTS)
    ): List<Long> {
        return if (remainingAdapters.isEmpty()) {
            currentChain + buildInAdapterJolts
        } else {
            val head = currentChain.last()
            val nextAdapter: Long = remainingAdapters
                .filter { it - head in ADAPTER_TOLERANCE }
                .minOrNull()!!

            makeAdapterChain(
                remainingAdapters = remainingAdapters - nextAdapter,
                currentChain = currentChain + nextAdapter
            )
        }
    }

    private fun List<Long>.countDifferences(n: Long): Long {
        return windowed(size = 2)
            .map {
                val a = it[0]
                val b = it[1]
                b - a
            }
            .count { it == n }
            .toLong()
    }

    override fun step1(): Long {
        val chain = makeAdapterChain(adapterList)
        return chain.countDifferences(1) * chain.countDifferences(3)
    }

    override fun step2(): Long {
        TODO("Not yet implemented")
    }
}