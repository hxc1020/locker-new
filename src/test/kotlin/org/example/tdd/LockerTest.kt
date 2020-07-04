package org.example.tdd

import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class LockerTest : StringSpec({

    "should get ticket when save bag given S size locker and available capacity is 1" {
        val locker = Locker(1, SizeType.S)

        val ticket = locker.save(Bag(SizeType.S))

        assert(ticket != null)
    }

    "should throw LockerIsFullException when save bag given M size locker and available capacity is 0" {
        val locker = Locker(1, SizeType.M)
        locker.save(Bag(SizeType.M))

        shouldThrow<LockerIsFullException> {
            locker.save(Bag(SizeType.M))
        }
    }

    "should get bag when take given S size locker and valid ticket" {
        val locker = Locker(1, SizeType.S)
        val ticket = locker.save(Bag(SizeType.S))

        val bag = locker.take(ticket)

        assert(bag != null)
    }

    "should throw SizeTypeMissMatchException when take given S size locker and valid M ticket" {
        val locker = Locker(1, SizeType.S)
        locker.save(Bag(SizeType.S))

        shouldThrow<SizeTypeMissMatchException> {
            locker.take(Ticket(SizeType.M))
        }
    }
})