package com.example.lib

class Phonebook(var name: String) {
    var phoneContacts = mutableListOf<PhoneUserContact>()

    fun add(user: PhoneUserContact) {
        phoneContacts.add(user)
    }

    fun sort() {
        phoneContacts.sort()
    }

    fun size(): Int {
        return phoneContacts.size
    }

    override fun toString(): String {
        var contacts: String = "$name:\n"
        for (phoneContact in phoneContacts) {
            contacts += "$phoneContact\n"
        }
        return contacts
    }
    fun stringFormat(): String {
        val pozdrav = "Hello"
        return String.format(pozdrav)
    }
}