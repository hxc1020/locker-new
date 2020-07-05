package org.example.tdd

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.shouldThrowExactly
import io.kotlintest.specs.StringSpec

class LockerRobotManagerTest : StringSpec({

    """should get ticket when manager save
        given S bag and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and both have available capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))

        val ticket = manager.save(Bag(SizeType.S))

        ticket shouldNotBe null
        ticket.type shouldBe SizeType.S
    }

    """should get ticket when manager save
        given manager manage M bag and 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and both have available capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))

        val ticket = manager.save(Bag(SizeType.M))

        ticket shouldNotBe null
        ticket.type shouldBe SizeType.M
    }

    """should get ticket when manager save
        given manager manage L bag and 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and both have available capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))

        val ticket = manager.save(Bag(SizeType.L))

        ticket shouldNotBe null
        ticket.type shouldBe SizeType.L
    }

    """should throw LockerIsFullException when manager save
        given L bag and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and locker has no capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))
        manager.save(Bag(SizeType.S))

        shouldThrowExactly<LockerIsFullException> {
            manager.save(Bag(SizeType.S))
        }
    }

    """should throw LockerIsFullException when manager save
        given L bag and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and primaryLockerRobot has no capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))
        manager.save(Bag(SizeType.M))

        shouldThrowExactly<LockerIsFullException> {
            manager.save(Bag(SizeType.M))
        }
    }

    """should throw LockerIsFullException when manager save
        given L bag and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
        and superLockerRobot has no capacity
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))
        manager.save(Bag(SizeType.L))

        shouldThrowExactly<LockerIsFullException> {
            manager.save(Bag(SizeType.L))
        }
    }

    """should get bag when manager take
        given valid S ticket and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))
        val givenBag = Bag(SizeType.S)

        val ticket = manager.save(givenBag)

        manager.take(ticket) shouldBe givenBag
    }

    """should get bag when manager take
        given valid M ticket and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))
        val givenBag = Bag(SizeType.M)

        val ticket = manager.save(givenBag)

        manager.take(ticket) shouldBe givenBag
    }

    """should get bag when manager take
        given valid L ticket and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))
        val givenBag = Bag(SizeType.L)

        val ticket = manager.save(givenBag)

        manager.take(ticket) shouldBe givenBag
    }

    """should throw TicketInvalidException when manager take
        given invalid empty type ticket and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))

        shouldThrowExactly<TicketInvalidException> {
            manager.take(Ticket())
        }
    }

    """should throw TicketInvalidException when manager take
        given invalid ticket and manager manage 1 S locker, 1 PrimaryLockerRobot,1 SuperLockerRobot
    """ {
        val locker = Locker(1, SizeType.S)
        val primaryLockerRobot = PrimaryLockerRobot(listOf(Locker(1, SizeType.M)))
        val superLockerRobot = SuperLockerRobot(listOf(Locker(1, SizeType.L)))
        val manager = LockerRobotManager(listOf(locker), listOf(primaryLockerRobot), listOf(superLockerRobot))

        shouldThrowExactly<TicketInvalidException> {
            manager.take(Ticket(SizeType.S))
        }
    }
})