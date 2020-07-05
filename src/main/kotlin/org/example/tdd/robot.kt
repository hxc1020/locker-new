package org.example.tdd

abstract class Robot(
        private val lockers: List<Locker>,
        private val acceptType: SizeType
) {
    fun take(ticket: Ticket): Bag? {
        val lockerWhichHasBag = lockers.firstOrNull { it.hasBag(ticket) } ?: throw TicketInvalidException()
        return lockerWhichHasBag.take(ticket)
    }

    protected fun checkAcceptLockerType() {
        if (lockers.any { it.type != acceptType })
            throw AcceptedLockerTypeWrongException("${this::class.simpleName} can only config ${acceptType.name} Locker!")
    }

    protected fun availableLockers(): List<Locker> {
        val availableLockers = lockers.filter { it.isFull().not() }
        if (availableLockers.isEmpty()) throw LockerIsFullException()
        return availableLockers
    }
}

class PrimaryLockerRobot(lockers: List<Locker>) : Robot(lockers, SizeType.M) {
    init {
        checkAcceptLockerType()
    }

    fun save(bag: Bag): Ticket {
        return availableLockers().first().save(bag)
    }
}

class SuperLockerRobot(lockers: List<Locker>) : Robot(lockers, SizeType.L) {

    init {
        checkAcceptLockerType()
    }

    fun save(bag: Bag): Ticket {
        return availableLockers().maxBy { it.availableCapacity() }!!.save(bag)
    }
}