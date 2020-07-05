package org.example.tdd

class LockerRobotManager(
        private val lockers: List<Locker>,
        primaryLockerRobots: List<PrimaryLockerRobot>,
        superLockerRobots: List<SuperLockerRobot>
) {
    fun save(bag: Bag): Ticket {
        return lockers.first().save(bag)
    }
}
