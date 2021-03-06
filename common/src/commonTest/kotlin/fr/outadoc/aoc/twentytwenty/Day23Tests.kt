package fr.outadoc.aoc.twentytwenty

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Tests {

    @Test
    @JsName("testDay23Step1")
    fun `Test day 23 step 1`() {
        assertEquals(53248976, Day23().step1())
    }

    @Test
    @JsName("testDay23Step2")
    fun `Test day 23 step 2`() {
        assertEquals(418819514477, Day23().step2())
    }
}