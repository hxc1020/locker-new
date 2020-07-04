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
})