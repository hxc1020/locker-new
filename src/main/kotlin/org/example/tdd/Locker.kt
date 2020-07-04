package org.example.tdd

class Locker(
        private val capacity: Int,
        private val type: SizeType
) {
    private val store = mutableMapOf<Ticket, Bag>()

    fun save(bag: Bag): Ticket {
        if (store.size >= capacity) throw LockerIsFullException()

        val ticket = Ticket(type)
        store[ticket] = bag
        return ticket
    }

    fun take(ticket: Ticket): Bag? {
        if (ticket.type != type) throw SizeTypeMissMatchException()
        if (store.containsKey(ticket).not()) throw TicketInvalidException()

        return store.remove(ticket)
    }
}