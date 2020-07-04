package org.example.tdd

class PrimaryLockerRobot(private val lockers: List<Locker>) {
    fun save(bag: Bag): Ticket {
        return lockers.first { it.isFull().not() }.save(bag)
    }
}