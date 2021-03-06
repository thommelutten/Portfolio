package util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.sun.jndi.cosnaming.IiopUrl
import models.AddressBook
import java.io.File
import java.io.FileNotFoundException

class FileParser {

    // Read file from json and convert to AddressBook

    companion object {
        fun loadAddressBook(path: String): AddressBook {
            try {
                val fileContent = File(path).readText()
                val moshi: Moshi = Moshi.Builder()
                    .build()
                val adapter: JsonAdapter<AddressBook> = moshi.adapter(AddressBook::class.java).lenient()
                val addressBook = adapter.fromJson(fileContent)
                if (addressBook != null) return addressBook
            } catch (e: Exception) {

            }
            return AddressBook()
        }

        fun saveAddressBook(path: String, addressBook: AddressBook): String {
            return try {
                val moshi: Moshi = Moshi.Builder()
                    .build()
                val adapter: JsonAdapter<AddressBook> = moshi.adapter(AddressBook::class.java).lenient()
                val jsonString = adapter.toJson(addressBook)
                File(path).writeText(jsonString)
                "Address book saved"
            } catch (e: Exception) {
                "Error happened"
            }
        }
    }

}