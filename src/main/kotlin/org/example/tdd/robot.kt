package org.example.tdd

class PrimaryLockerRobot(private val lockers: List<Locker>) {
    private val acceptType = SizeType.M

    init {
        if (lockers.any { it.type != acceptType })
            throw AcceptedLockerTypeWrongException("Primary Locker Robot can only config M Locker!")
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
    fun save(bag: Bag): Ticket {
        return lockers.filter { it.isFull().not() }.maxBy { it.availableCapacity() }!!.save(bag)
    }

}