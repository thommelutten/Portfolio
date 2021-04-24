package commands

import helpers.createAddressBookWithContacts
import models.AddressBook

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UpdateCommandTest {
    @Test
    fun updateWrongArgumentsContactTest() {
        val addressBook = AddressBook()

        val output = UpdateCommand().loadAddressBook(addressBook).withArgs(arrayOf("")).execute()
        Assertions.assertEquals("Wrong arguments passed. Format: Update FirstName LastName [-FirstName -LastName -Email -Phone]", output)
    }

    @Test
    fun updateNameOnNonExistingContactTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Freya", "Odinsson", "-FirstName", "Simone")
        val output = UpdateCommand().loadAddressBook(addressBook).withArgs(inputArgs).execute()
        Assertions.assertEquals("Unable to update. No such contact found", output)
    }

    @Test
    fun updateNameOnExistingContactTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-FirstName", "Simon")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)
        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Updated Contact", output)

        val updatedContact = updateCommand.addressBook.findContact("Simon Odinsson")
        Assertions.assertEquals("Simon", updatedContact.firstName)
        Assertions.assertEquals("Odinsson", updatedContact.lastName)
    }

    @Test
    fun updateLastNameOnExistingContactTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-LastName", "Says")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)
        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Updated Contact", output)
    }

    @Test
    fun updateBadLastNameTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-LastName", "1234")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)
        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Unable to update. Invalid lastname", output)
    }

    @Test
    fun updateBadFirstNameTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-FirstName", "1234")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)
        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Unable to update. Invalid firstname", output)
    }

    @Test
    fun updateBadEmailTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-Email", "1234")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)
        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Unable to update. Invalid email", output)
    }

    @Test
    fun updateBadPhoneTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-Phone", "a1234")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)
        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Unable to update. Invalid phone", output)
    }

    @Test
    fun updateMultipleValuesOnExistingContactTest() {
        val addressBook = createAddressBookWithContacts()
        val inputArgs = arrayOf("Update", "Thor", "Odinsson", "-FirstName", "Simon", "-Phone", "12345678")
        val updateCommand = UpdateCommand().loadAddressBook(addressBook)

        val originalContact = updateCommand.addressBook.findContact("Thor Odinsson")
        Assertions.assertEquals("Thor", originalContact.firstName)
        Assertions.assertEquals("Odinsson", originalContact.lastName)
        Assertions.assertEquals("11223344", originalContact.phone)

        val output = updateCommand.withArgs(inputArgs).execute()
        Assertions.assertEquals("Updated Contact", output)

        val updatedContact = updateCommand.addressBook.findContact("Simon Odinsson")
        Assertions.assertEquals("Simon", updatedContact.firstName)
        Assertions.assertEquals("Odinsson", updatedContact.lastName)
        Assertions.assertEquals("12345678", updatedContact.phone)

        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            addressBook.findContact("Thor Odinsson")
        }
        Assertions.assertEquals("Contact not found", exception.message)
    }
}