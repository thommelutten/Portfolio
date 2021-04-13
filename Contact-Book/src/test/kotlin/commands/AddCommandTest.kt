package commands

import models.AddressBook
import org.junit.Assert
import org.junit.jupiter.api.Test

class AddCommandTest {

    @Test
    fun addCommandWithNotEnoughArgsTest() {
        val addressBook = AddressBook()

        val output = AddCommand().loadAddressBook(addressBook).withArgs(arrayOf("")).execute()
        Assert.assertEquals("Wrong arguments passed. Format: Add FirstName LastName Phone Email", output)
    }

    @Test
    fun addCommandWithCorrectArgsTest() {
        val addressBook = AddressBook()

        val args = arrayOf(
            "Add",
            "Thor",
            "Odinsson",
            "11223344",
            "thor@asgard.com"
        )

        val addCommand = AddCommand()
        val output = addCommand.loadAddressBook(addressBook).withArgs(args).execute()

        Assert.assertEquals("Contact added", output)
        Assert.assertEquals(1, addCommand.addressBook.getContacts().size)
    }
}