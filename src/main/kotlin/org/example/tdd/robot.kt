package org.example.tdd

class PrimaryLockerRobot(private val lockers: List<Locker>) {
    fun save(bag: Bag): Ticket {
        return lockers[0].save(bag)
    }
}