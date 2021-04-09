package models


class AddressBook (private val contactList: MutableList<Contact> = mutableListOf()){
    fun getContacts(): List<Contact> {
        return contactList
    }

    fun addContact(firstName: String, lastName: String, email: String, phone: String) {
        contactList.add(Contact(firstName, lastName, email, phone))
    }

    fun addContact(contact: Contact) {
        contactList.add(contact)
    }

    fun addContacts(contacts: MutableList<Contact>) {
        contactList.addAll(contacts)
    }

    fun removeContact(firstName: String, lastName: String) {
        contactList.removeIf {
            it.firstName == firstName && it.lastName == lastName
        }
    }

    fun findContact(name: String): Contact {
        return contactList.find { c -> (c.firstName == name || c.lastName == name) } ?: throw NoSuchElementException("Contact not found")
    }

    fun searchContacts(query: String): List<Contact> {
        return contactList.filter {c -> (c.firstName.contains(query) || c.lastName.contains(query))  }
    }
}