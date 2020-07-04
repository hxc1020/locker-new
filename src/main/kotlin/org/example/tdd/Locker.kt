package org.example.tdd

class Locker(
        private val capacity: Int,
        private val type: SizeType
) {
    private val store = mutableMapOf<Ticket, Bag>()

    fun isFull() = store.size >= capacity

    fun save(bag: Bag): Ticket {
        if (isFull()) throw LockerIsFullException()

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