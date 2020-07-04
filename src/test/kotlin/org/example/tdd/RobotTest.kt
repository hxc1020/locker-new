package org.example.tdd

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class RobotTest : StringSpec({

    "should saved by locker1 and get ticket when robot save " +
            "given PrimaryLockerRobot manage M locker1 and locker2 both have available capacity" {
                val locker1 = Locker(1, SizeType.M)
                val locker2 = Locker(1, SizeType.M)
                val robot = PrimaryLockerRobot(listOf(locker1, locker2))
                val givenBag = Bag(SizeType.M)

                val ticket = robot.save(givenBag)

                locker1.take(ticket) shouldBe givenBag
            }
})