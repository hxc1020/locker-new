package org.example.tdd

class PrimaryLockerRobot(private val lockers: List<Locker>) {
    private val acceptType = SizeType.M

    init {
        if (lockers.any { it.type != acceptType })
            throw AcceptedLockerTypeWrongException("PrimaryLockerRobot can only config M Locker!")
    }

    fun save(bag: Bag): Ticket {
        val availableLocker = lockers.firstOrNull { it.isFull().not() } ?: throw LockerIsFullException()
        return availableLocker.save(bag)
    }

    fun take(ticket: Ticket): Bag? {
        val lockerWhichHasBag = lockers.firstOrNull { it.hasBag(ticket) } ?: throw TicketInvalidException()
        return lockerWhichHasBag.take(ticket)
    }
}

class SuperLockerRobot(private val lockers: List<Locker>) {
    private val acceptType = SizeType.L

    init {
        if (lockers.any { it.type != acceptType })
            throw AcceptedLockerTypeWrongException("SuperLockerRobot can only config L Locker!")
    }

    fun save(bag: Bag): Ticket {
        val availableLockers = lockers.filter { it.isFull().not() }
        if (availableLockers.isEmpty()) throw LockerIsFullException()
        return availableLockers.maxBy { it.availableCapacity() }!!.save(bag)
    }

    fun take(ticket: Ticket): Bag? {
        val lockerWhichHasBag = lockers.firstOrNull { it.hasBag(ticket) } ?: throw TicketInvalidException()
        return lockerWhichHasBag.take(ticket)
    }

}