package commands;

import models.AddressBook
import models.Contact
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test;

class DeleteCommandTest {

    @Test
    fun deleteCommandWithNotEnoughArgsTest() {
        val addressBook = AddressBook()

        val output = DeleteCommand().loadAddressBook(addressBook).withArgs(arrayOf("")).execute()
        Assertions.assertEquals("Wrong arguments passed. Format: Delete FirstName LastName", output)
    }

    @Test
    fun deleteCommandWithExistingContacts() {
        val addressBook = createAddressBookWithContacts()
        Assertions.assertEquals("Odinsson", addressBook.findContact("Thor").lastName)
        Assertions.assertEquals(3, addressBook.getContacts().size)

        val deleteCommand = DeleteCommand().loadAddressBook(addressBook)

        val output = deleteCommand.withArgs(arrayOf("Delete", "Thor", "Odinsson")).execute()
        Assertions.assertEquals("Contact removed", output)
        Assertions.assertEquals(2, deleteCommand.addressBook.getContacts().size)

        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            deleteCommand.addressBook.findContact("Thor Ordinsson")
        }
        Assertions.assertEquals("Contact not found", exception.message)
    }

    private fun createAddressBookWithContacts(): AddressBook {
        return AddressBook(mutableListOf(
            Contact("Simon", "Says", "test@test.com", "55443322"),
            Contact("Thor", "Odinsson", "thor@asgard.com", "11223344"),
            Contact("Odin", "Allfather", "odin@asgard.com", "66554433")
        ))
    }
}
