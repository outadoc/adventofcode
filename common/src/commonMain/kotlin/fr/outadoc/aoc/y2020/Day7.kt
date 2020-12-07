package fr.outadoc.aoc.y2020

import fr.outadoc.aoc.scaffold.Day
import fr.outadoc.aoc.scaffold.Year

class Day7 : Day(Year._2020) {

    private val containerRegex = Regex("^([a-z ]+) bags contain .+$")
    private val contentsRegex = Regex(" ([0-9]+) ([a-z ]+) bags?[,.]")

    private val rules = readDayInput()
        .lineSequence()
        .filterNot { it.isEmpty() }
        .parse()

    data class Rule(val color: String, val contents: List<Content>) {
        data class Content(val count: Int, val color: String)
    }

    private fun Sequence<String>.parse(): Sequence<Rule> = map { rule ->
        val nameResult = containerRegex.find(rule)!!
        val contentsResult = contentsRegex.findAll(rule)

        Rule(
            color = nameResult.groupValues[1],
            contents = contentsResult.map { res ->
                Rule.Content(
                    count = res.groupValues[1].toInt(),
                    color = res.groupValues[2]
                )
            }.toList()
        )
    }

    data class Bag(val color: String, var contents: List<Content> = emptyList()) {
        data class Content(val count: Int, val bag: Bag)

        val size: Long
            get() = 1 + contents.sumOf { (count, bag) ->
                count * bag.size
            }

        fun contains(bag: Bag): Boolean = when {
            contents.any { (_, containedBag) -> containedBag == bag } -> true
            else -> contents.any { (_, containedBag) ->
                containedBag.contains(bag)
            }
        }
    }

    private val bags = rules.map { rule ->
        rule.color to Bag(color = rule.color)
    }.toMap()

    private fun bagByName(name: String): Bag = bags[name]!!

    init {
        // Initialize bag contents
        rules.forEach { rule ->
            bagByName(rule.color).apply {
                contents = rule.contents.map { (count, bagName) ->
                    Bag.Content(
                        count = count,
                        bag = bagByName(bagName)
                    )
                }
            }
        }
    }

    override fun step1(): Long {
        val target = bagByName("shiny gold")
        return bags.values.count { bag -> bag.contains(target) }.toLong()
    }

    override fun step2(): Long {
        val target = bagByName("shiny gold")
        return target.size - 1
    }
}