package org.example.tdd

class Locker(
        private val capacity: Int,
        private val type: SizeType
) {
    private val store = mutableMapOf<Ticket, Bag>()

    fun save(bag: Bag): Ticket {
        if (store.size >= capacity) {
            throw LockerIsFullException()
        }
        val ticket = Ticket(type)
        store[ticket] = bag
        return ticket
    }

    fun take(ticket: Ticket): Bag? {
        return store.remove(ticket)
    }
}