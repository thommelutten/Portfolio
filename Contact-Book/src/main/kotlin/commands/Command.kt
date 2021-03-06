package commands

import models.AddressBook
import util.FileParser

interface Command {
    var addressBookPath: String
    var addressBook: AddressBook
    var args: Map<String,List<String>>

    fun execute() : String

    fun loadAddressBook(path: String): Command {
        this.addressBookPath = path
        this.addressBook = FileParser.loadAddressBook(path)
        return this
    }

    fun loadAddressBook(addressBook: AddressBook): Command {
        this.addressBook = addressBook
        return this
    }

    fun withArgs(args: Array<String>): Command {
        this.args = args.fold(
            Pair(emptyMap<String, List<String>>(), "")) {
                (map, lastKey), elem ->
            if (elem.startsWith("-"))  {
                Pair(map + (elem to emptyList()), elem)
            }
            else {
                Pair(map + (lastKey to map.getOrDefault(lastKey, emptyList()) + elem), lastKey)
            }
        }.first
        return this
    }

    fun saveAddressBook(): String {
        return try{
            FileParser.saveAddressBook(addressBookPath, addressBook)
            "Change saved"
        } catch (e: Exception) {
            "Unable to save change"
        }
    }
}