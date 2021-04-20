package util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import models.AddressBook
import models.Contact
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FileParserTest {

    @Test
    fun moshiWriteTest() {
        val moshi: Moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<AddressBook> = moshi.adapter(AddressBook::class.java).lenient()
        val addressBook = createAddressBookWithContacts()
        val jsonString = adapter.toJson(addressBook)
        println(jsonString)
    }

    @Test
    fun moshiReadTest() {
        val jsonString = "{\"contactList\":[{\"firstName\":\"Simon\",\"lastName\":\"Says\",\"email\":\"test@test.com\",\"phone\":\"55443322\"},{\"firstName\":\"Thor\",\"lastName\":\"Odinsson\",\"email\":\"thor@asgard.com\",\"phone\":\"11223344\"},{\"firstName\":\"Odin\",\"lastName\":\"Allfather\",\"email\":\"odin@asgard.com\",\"phone\":\"66554433\"}]}\n"
        val moshi: Moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<AddressBook> = moshi.adapter(AddressBook::class.java).lenient()
        val addressBook = adapter.fromJson(jsonString)

        Assertions.assertEquals("Simon", addressBook?.getContacts()?.get(0)?.firstName)
    }

    @Test
    fun readFileFromResourceDirectlyMTest() {
        val fileContent = object {}.javaClass.getResource("/AddressBookExample.json").readText()
        val moshi: Moshi = Moshi.Builder()
            .build()
        val adapter: JsonAdapter<AddressBook> = moshi.adapter(AddressBook::class.java).lenient()
        val addressBook = adapter.fromJson(fileContent)

        Assertions.assertEquals("Simon", addressBook?.getContacts()?.get(0)?.firstName)
    }

    @Test
    fun readJsonFileTest() {
        val resource = object {}.javaClass.getResource("/AddressBookExample.json")
        val addressBook = FileParser.loadAddressBook(resource.path)

        Assertions.assertEquals("Simon", addressBook.getContacts()[0].firstName)
    }

    @Test
    fun returnEmptyAddressBookWhenNoFileFound() {
        val addressBook = FileParser.loadAddressBook("")

        Assertions.assertEquals(0, addressBook.getContacts().size)
    }

    private fun createAddressBookWithContacts(): AddressBook {
        return AddressBook(mutableListOf(
            Contact("Simon", "Says", "test@test.com", "55443322"),
            Contact("Thor", "Odinsson", "thor@asgard.com", "11223344"),
            Contact("Odin", "Allfather", "odin@asgard.com", "66554433")
        ))
    }
}