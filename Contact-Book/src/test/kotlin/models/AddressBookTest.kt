package models

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AddressBookTest {
    private val c = Contact("Simon", "Says", "s@test.com", "55443322")

    @Test
    fun addressBookIsEmptyTest() {
        val addressBook = AddressBook()

        Assertions.assertTrue(addressBook.getContacts().isEmpty())
    }

    @Test
    fun addContactToAddressBookAsContactBookTest() {
        val addressBook = AddressBook()
        addressBook.addContact(c)

        Assertions.assertEquals(1, addressBook.getContacts().size)
        Assertions.assertEquals(c, addressBook.getContacts().first())
    }

    @Test
    fun addContactToAddressBookAsValuesTest(){
        val addressBook = AddressBook()
        addressBook.addContact("Simon", "Says", "s@test.com", "55443322")

        Assertions.assertEquals(1, addressBook.getContacts().size)

        val contact = addressBook.getContacts().first()

        Assertions.assertEquals("Simon", contact.firstName)
        Assertions.assertEquals("Says", contact.lastName)
        Assertions.assertEquals("s@test.com", contact.email)
        Assertions.assertEquals("55443322", contact.phone)
    }

    @Test
    fun removeContactFromAddressBookTest() {
        val addressBook = AddressBook()
        addressBook.addContact(c)

        Assertions.assertEquals(1, addressBook.getContacts().size)

        addressBook.removeContact(c.firstName, c.lastName)
        Assertions.assertEquals(0, addressBook.getContacts().size)
    }

    @Test
    fun addMultipleContactsToAddressBookTest() {
        val addressBook = AddressBook()
        val contacts = createListOfContacts()

        addressBook.addContacts(contacts)
        Assertions.assertEquals(contacts, addressBook.getContacts())
    }

    @Test
    fun findSpecificContactFromAddressBookTest() {
        val addressBook = AddressBook()

        val contacts = createListOfContacts()
        addressBook.addContacts(contacts)

        val contact = addressBook.findContact("Simon")

        Assertions.assertEquals(contacts.first(), contact)
    }

    @Test
    fun noContactFoundFromAddressBookTest() {
        val addressBook = AddressBook()

        val contacts = createListOfContacts()
        addressBook.addContacts(contacts)

        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            addressBook.findContact("Theo")
        }
        Assertions.assertEquals("Contact not found", exception.message)
    }

    @Test
    fun listContactsFromAddressBook() {
        val addressBook = AddressBook()

        val contacts = createListOfContacts()
        addressBook.addContacts(contacts)

        val foundContacts = addressBook.searchContacts("Odin")

        val filteredContacts = contacts.filter {c -> (c.firstName.contains("Odin") || c.lastName.contains("Odin"))  }

        Assertions.assertEquals(2, foundContacts.size)
        Assertions.assertEquals(filteredContacts, foundContacts)
    }

    private fun createListOfContacts(): MutableList<Contact> {
        return mutableListOf(
            Contact("Simon", "Says", "test@test.com", "55443322"),
            Contact("Thor", "Odinsson", "thor@asgard.com", "11223344"),
            Contact("Odin", "Allfather", "odin@asgard.com", "66554433")
        )
    }
}