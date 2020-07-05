package org.example.tdd

class LockerRobotManager(
        private val lockers: List<Locker>,
        private val primaryLockerRobots: List<PrimaryLockerRobot>,
        private val superLockerRobots: List<SuperLockerRobot>
) {
    fun save(bag: Bag): Ticket {
        return when (bag.type) {
            SizeType.S -> (lockers.find { it.available() } ?: throw LockerIsFullException()).save(bag)
            SizeType.M -> (primaryLockerRobots.find { it.available() } ?: throw LockerIsFullException()).save(bag)
            SizeType.L -> (superLockerRobots.find { it.available() } ?: throw LockerIsFullException()).save(bag)
        }
    }

    fun take(ticket: Ticket): Bag? {
        return when (ticket.type) {
            SizeType.S -> (lockers.find { it.hasBag(ticket) } ?: throw TicketInvalidException()).take(ticket)
            SizeType.M -> (primaryLockerRobots.find { it.hasBag(ticket) } ?: throw TicketInvalidException()).take(ticket)
            SizeType.L -> (superLockerRobots.find { it.hasBag(ticket) } ?: throw TicketInvalidException()).take(ticket)
            else -> throw TicketInvalidException()
        }
    }
}
