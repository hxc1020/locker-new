package org.example.tdd

import io.kotlintest.matchers.string.shouldStartWith
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowExactly
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

    """should saved by locker1 and get ticket when robot save 
        given SuperLockerRobot manage L locker1 and locker2 and available capacity is 3 and 2"""{
        val locker1 = Locker(3, SizeType.L)
        val locker2 = Locker(2, SizeType.L)
        val robot = SuperLockerRobot(listOf(locker1, locker2))
        val givenBag = Bag(SizeType.L)

        val ticket = robot.save(givenBag)

        locker1.take(ticket) shouldBe givenBag
    }

    """should throw LockerIsFullException when robot save 
        given SuperLockerRobot manage L locker1 and locker2 and both have no available capacity"""{
        val locker1 = Locker(1, SizeType.L)
        val locker2 = Locker(1, SizeType.L)
        val robot = SuperLockerRobot(listOf(locker1, locker2))
        robot.save(Bag(SizeType.L))
        robot.save(Bag(SizeType.L))

        shouldThrowExactly<LockerIsFullException> {
            robot.save(Bag(SizeType.L))
        }
    }

    "should get bag when robot take given SuperLockerRobot manage L locker1 and locker2 and valid L ticket" {
        val locker1 = Locker(1, SizeType.L)
        val locker2 = Locker(1, SizeType.L)
        val robot = SuperLockerRobot(listOf(locker1, locker2))
        val givenBag = Bag(SizeType.L)

        val ticket = robot.save(givenBag)

        robot.take(ticket) shouldBe givenBag
    }

    "should throw TicketInvalidException when robot take given SuperLockerRobot manage L locker1 and locker2 and invalid ticket" {
        val locker1 = Locker(1, SizeType.L)
        val locker2 = Locker(1, SizeType.L)
        val robot = SuperLockerRobot(listOf(locker1, locker2))

        shouldThrowExactly<TicketInvalidException> {
            robot.take(Ticket(SizeType.L))
        }
    }

    "should throw AcceptedLockerTypeWrongException when new robot given SuperLockerRobot and S Locker" {
        val locker1 = Locker(1, SizeType.S)

        val exception = shouldThrowExactly<AcceptedLockerTypeWrongException> {
            SuperLockerRobot(listOf(locker1))
        }
        exception.message shouldStartWith  "SuperLockerRobot"
    }
})