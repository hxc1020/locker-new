package org.example.tdd

import io.kotlintest.specs.StringSpec

class LockerTest : StringSpec({

    "should get ticket when save bag given S size locker and available capacity is 1" {
        val locker = Locker(1, SizeType.S)

        val ticket = locker.save(Bag(SizeType.S))

        assert(ticket != null)
    }
})