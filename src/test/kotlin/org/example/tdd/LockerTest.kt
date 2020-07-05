package org.example.tdd

import io.kotlintest.shouldNotBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class LockerTest : StringSpec({

    "should get ticket when save bag given S size locker and available capacity is 1" {
        val locker = Locker(1, SizeType.S)

        val ticket = locker.save(Bag(SizeType.S))

        ticket shouldNotBe null
    }

    "should throw LockerIsFullException when save bag given M size locker and available capacity is 0" {
        val locker = Locker(1, SizeType.M)
        locker.save(Bag(SizeType.M))

        shouldThrow<LockerIsFullException> {
            locker.save(Bag(SizeType.M))
        }
    }

    "should throw SizeTypeMissMatchException when save bag given M size locker and S bag" {
        val locker = Locker(1, SizeType.M)

        shouldThrow<SizeTypeMissMatchException> {
            locker.save(Bag(SizeType.S))
        }
    }

    "should get bag when take given S size locker and valid ticket" {
        val locker = Locker(1, SizeType.S)
        val ticket = locker.save(Bag(SizeType.S))

        val bag = locker.take(ticket)

        bag shouldNotBe null
    }

    "should throw SizeTypeMissMatchException when take given S size locker and valid M ticket" {
        val locker = Locker(1, SizeType.S)
        locker.save(Bag(SizeType.S))

        shouldThrow<TicketTypeMissMatchException> {
            locker.take(Ticket(SizeType.M))
        }
    }

    "should throw TicketInvalidException when take given S size locker and invalid ticket" {
        val locker = Locker(1, SizeType.S)
        locker.save(Bag(SizeType.S))

        shouldThrow<TicketInvalidException> {
            locker.take(Ticket(SizeType.S))
        }
    }
})