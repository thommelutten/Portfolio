package commands

import models.AddressBook
import models.Contact
import org.junit.Assert
import org.junit.jupiter.api.Test

class SearchCommandTest {
    @Test
    fun searchCommandWithNotEnoughArgsTest() {
        val output = SearchCommand().loadAddressBook(createAddressBookWithContacts()).withArgs(arrayOf("Thor")).execute()
        Assert.assertEquals("Wrong arguments passed. Format: Search Name", output)
    }

    @Test
    fun searchCommandWithTwoResultsTest() {
        val output = SearchCommand().loadAddressBook(createAddressBookWithContacts()).withArgs(arrayOf("Search","Odin")).execute()

        val desiredOutput = (
                "Thor Odinsson\t\tthor@asgard.com\t\t11223344\n" +
                        "Odin Allfather\t\todin@asgard.com\t\t66554433\n"
                )
        Assert.assertEquals(desiredOutput, output)
    }
}