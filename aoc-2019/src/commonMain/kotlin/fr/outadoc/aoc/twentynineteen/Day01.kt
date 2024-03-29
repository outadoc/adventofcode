package fr.outadoc.aoc.twentynineteen

import fr.outadoc.aoc.scaffold.Day
import fr.outadoc.aoc.scaffold.readDayInput
import kotlin.math.floor

class Day01 : Day<Long> {

    private val masses: Sequence<Double> =
        readDayInput()
            .lines()
            .asSequence()
            .filter { it.isNotBlank() }
            .map { it.toDouble() }

    private fun fuelNeededForMass(mass: Double) = floor(mass / 3) - 2

    override fun step1(): Long = masses
        .map { mass -> fuelNeededForMass(mass) }
        .sum()
        .toLong()

    private fun fuelNeededForMassAndFuel(mass: Double): Double {
        val fuelNeeded = fuelNeededForMass(mass)

        if (fuelNeeded <= 0) {
            return .0
        }

        return fuelNeeded + fuelNeededForMassAndFuel(fuelNeeded)
    }

    override fun step2(): Long {
        return masses
            .map { mass -> fuelNeededForMassAndFuel(mass) }
            .sum()
            .toLong()
    }

    override val expectedStep1: Long = 3512133
    override val expectedStep2: Long = 5265294
}
