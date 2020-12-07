package fr.outadoc.aoc.y2020

import kotlin.test.Test
import kotlin.test.assertEquals

class UnitTests2020 {

    @Test
    fun testDay1Step1() {
        assertEquals(786811, Day1().step1())
    }

    @Test
    fun testDay1Step2() {
        assertEquals(199068980, Day1().step2())
    }

    @Test
    fun testDay2Step1() {
        assertEquals(591, Day2().step1())
    }

    @Test
    fun testDay2Step2() {
        assertEquals(335, Day2().step2())
    }

    @Test
    fun testDay3Step1() {
        assertEquals(276, Day3().step1())
    }

    @Test
    fun testDay3Step2() {
        assertEquals(7812180000, Day3().step2())
    }

    @Test
    fun testDay4Step1() {
        assertEquals(260, Day4().step1())
    }

    @Test
    fun testDay4Step2() {
        assertEquals(153, Day4().step2())
    }

    @Test
    fun testDay5Step1() {
        assertEquals(915, Day5().step1())
    }

    @Test
    fun testDay5Step2() {
        assertEquals(699, Day5().step2())
    }

    @Test
    fun testDay6Step1() {
        assertEquals(6273, Day6().step1())
    }

    @Test
    fun testDay6Step2() {
        assertEquals(3254, Day6().step2())
    }

    @Test
    fun testDay7Step1() {
        assertEquals(332, Day7().step1())
    }

    @Test
    fun testDay7Step2() {
        assertEquals(10875, Day7().step2())
    }
}