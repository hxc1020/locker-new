package org.example.tdd

import io.kotlintest.matchers.string.shouldStartWith
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowExactly
import io.kotlintest.specs.StringSpec

class PrimaryLockerRobotTest : StringSpec({

    """should saved by locker1 and get ticket when robot save 
        given PrimaryLockerRobot manage M locker1 and locker2 both have available capacity"""{
        val locker1 = Locker(1, SizeType.M)
        val locker2 = Locker(1, SizeType.M)
        val robot = PrimaryLockerRobot(listOf(locker1, locker2))
        val givenBag = Bag(SizeType.M)

        val ticket = robot.save(givenBag)

        locker1.take(ticket) shouldBe givenBag
    }

    """should saved by locker2 and get ticket when robot save
        given PrimaryLockerRobot manage M locker1 and locker2 and locker1 has no available capacity"""{
        val locker1 = Locker(1, SizeType.M)
        val locker2 = Locker(1, SizeType.M)
        locker1.save(Bag(SizeType.M))
        val robot = PrimaryLockerRobot(listOf(locker1, locker2))
        val givenBag = Bag(SizeType.M)

        val ticket = robot.save(givenBag)

        locker2.take(ticket) shouldBe givenBag
    }

    """should throw LockerIsFullException when robot save
        given PrimaryLockerRobot manage M locker1 and locker2 and both has no available capacity""" {
        val locker1 = Locker(1, SizeType.M)
        val locker2 = Locker(1, SizeType.M)
        locker1.save(Bag(SizeType.M))
        locker2.save(Bag(SizeType.M))
        val robot = PrimaryLockerRobot(listOf(locker1, locker2))

        shouldThrowExactly<LockerIsFullException> {
            robot.save(Bag(SizeType.M))
        }
    }

    "should get bag when robot take given PrimaryLockerRobot manage M locker1 and locker2 and valid M ticket" {
        val locker1 = Locker(1, SizeType.M)
        val locker2 = Locker(1, SizeType.M)
        val robot = PrimaryLockerRobot(listOf(locker1, locker2))
        val givenBag = Bag(SizeType.M)

        val ticket = robot.save(givenBag)

        robot.take(ticket) shouldBe givenBag
    }

    "should throw TicketInvalidException when robot take given PrimaryLockerRobot manage M locker1 and locker2 and invalid ticket" {
        val locker1 = Locker(1, SizeType.M)
        val locker2 = Locker(1, SizeType.M)
        val robot = PrimaryLockerRobot(listOf(locker1, locker2))

        shouldThrowExactly<TicketInvalidException> {
            robot.take(Ticket(SizeType.M))
        }
    }

    "should throw AcceptedLockerTypeWrongException when new robot given PrimaryLockerRobot and S Locker" {
        val locker1 = Locker(1, SizeType.S)

        val exception = shouldThrowExactly<AcceptedLockerTypeWrongException> {
            PrimaryLockerRobot(listOf(locker1))
        }
        exception.message shouldStartWith  "PrimaryLockerRobot"
    }
})