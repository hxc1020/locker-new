package org.example.tdd

class LockerIsFullException : Throwable()

class SizeTypeMissMatchException : Throwable()

class TicketTypeMissMatchException : Throwable()

class AcceptedLockerTypeWrongException(message: String): Throwable(message)

class TicketInvalidException: Throwable()
