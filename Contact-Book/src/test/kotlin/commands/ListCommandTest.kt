package commands

import models.AddressBook
import models.Contact
import helpers.createAddressBookWithContacts
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListCommandTest {

    @Test
    fun listCommandWithEmptyAddressBookTest() {
        val addressBook = AddressBook()

        val output = ListCommand().loadAddressBook(addressBook).execute()
        Assertions.assertEquals("Address Book is Empty", output)
    }

    @Test
    fun listCommandWithFilledAddressBookTest() {
        val addressBook = createAddressBookWithContacts()

        val output = ListCommand().loadAddressBook(addressBook).execute()
        val desiredOutput = (
                "Simon Says\t\ttest@test.com\t\t55443322\n" +
                        "Thor Odinsson\t\tthor@asgard.com\t\t11223344\n" +
                        "Odin Allfather\t\todin@asgard.com\t\t66554433\n"
                )
        Assertions.assertEquals(desiredOutput, output)
    }

    @Test
    fun listCommandWithBlankAddressFieldsTest() {
        val addressBook = AddressBook(mutableListOf(
            Contact("Simon", "Says", "test@test.com", ""),
            Contact("Thor", "Odinsson", "", "11223344")
        ))

        val output = ListCommand().loadAddressBook(addressBook).execute()
        val desiredOutput = (
                "Simon Says\t\ttest@test.com\t\t\n" +
                        "Thor Odinsson\t\t\t\t11223344\n"
                )

        Assertions.assertEquals(desiredOutput, output)
    }
}