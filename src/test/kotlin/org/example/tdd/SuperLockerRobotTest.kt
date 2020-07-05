package org.example.tdd

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class SuperLockerRobotTest : StringSpec({

    """should saved by locker2 and get ticket when robot save 
        given SuperLockerRobot manage L locker1 and locker2 and available capacity is 2 and 3"""{
        val locker1 = Locker(2, SizeType.L)
        val locker2 = Locker(3, SizeType.L)
        val robot = SuperLockerRobot(listOf(locker1, locker2))
        val givenBag = Bag(SizeType.L)

        val ticket = robot.save(givenBag)

        locker2.take(ticket) shouldBe givenBag
    }
})