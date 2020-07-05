package org.example.tdd

class PrimaryLockerRobot(private val lockers: List<Locker>) {
    fun save(bag: Bag): Ticket {
        val availableLocker = lockers.firstOrNull { it.isFull().not() } ?: throw LockerIsFullException()
        return availableLocker.save(bag)
    }
}