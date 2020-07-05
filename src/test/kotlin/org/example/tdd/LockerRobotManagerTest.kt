package org.example.tdd

import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec

class LockerRobotManagerTest : StringSpec({
    """should get ticket when manager save
        given manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and both have available capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))

        val ticket = manager.save(Bag(SizeType.S))

        ticket shouldNotBe null
    }
})