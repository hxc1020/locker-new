package org.example.tdd

class Locker(
        private val capacity: Int,
        private val type: SizeType
) {
    private val store = mutableMapOf<Ticket, Bag>()

    fun isFull() = store.size >= capacity

    fun hasBag(ticket: Ticket) = store.contains(ticket)

    fun save(bag: Bag): Ticket {
        if (bag.type != type) throw SizeTypeMissMatchException()
        if (isFull()) throw LockerIsFullException()

        val ticket = Ticket(type)
        store[ticket] = bag
        return ticket
    }

    fun take(ticket: Ticket): Bag? {
        if (ticket.type != type) throw TicketTypeMissMatchException()
        if (hasBag(ticket).not()) throw TicketInvalidException()

        return store.remove(ticket)
    }
}