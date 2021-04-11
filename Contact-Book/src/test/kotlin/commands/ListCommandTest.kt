package commands

import models.AddressBook
import models.Contact
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListCommandTest {

    private val c = Contact("Simon", "Says", "s@test.com", "55443322")

    @Test
    fun listCommandWithEmptyAddressBookTest() {
        val addressBook = AddressBook()

        val output = ListCommand(addressBook).execute()
        Assert.assertEquals("Address Book is Empty", output)
    }

    @Test
    fun listCommandWithFilledAddressBookTest() {
        val addressBook = createAddressBookWithContacts()

        val output = ListCommand(addressBook).execute()
        val desiredOutput = (
                "Simon Says\t\ttest@test.com\t\t55443322\n" +
                        "Thor Odinsson\t\tthor@asgard.com\t\t11223344\n" +
                        "Odin Allfather\t\todin@asgard.com\t\t66554433\n"
                )
        Assert.assertEquals(desiredOutput, output)
    }

    @Test
    fun listCommandWithBlankAddressFieldsTest() {
        val addressBook = AddressBook(mutableListOf(
            Contact("Simon", "Says", "test@test.com", ""),
            Contact("Thor", "Odinsson", "", "11223344")
        ))

        val output = ListCommand(addressBook).execute()
        val desiredOutput = (
                "Simon Says\t\ttest@test.com\t\t\n" +
                        "Thor Odinsson\t\t\t\t11223344\n"
                )

        Assert.assertEquals(desiredOutput, output)
    }

    private fun createAddressBookWithContacts(): AddressBook {
        return AddressBook(mutableListOf(
            Contact("Simon", "Says", "test@test.com", "55443322"),
            Contact("Thor", "Odinsson", "thor@asgard.com", "11223344"),
            Contact("Odin", "Allfather", "odin@asgard.com", "66554433")
        ))
    }

}