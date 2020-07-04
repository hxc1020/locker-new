package org.example.tdd

class Locker(private val capacity: Int, type: SizeType) {
    private val store = mutableMapOf<Ticket, Bag>()

    fun save(bag: Bag): Ticket {
        if (store.size >= capacity) {
            throw LockerIsFullException()
        }
        val ticket = Ticket()
        store[ticket] = bag
        return ticket
    }
}