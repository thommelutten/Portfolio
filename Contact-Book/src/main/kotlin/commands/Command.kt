package commands

import models.AddressBook
import util.FileParser

interface Command {
    var addressBook: AddressBook
    var args: Array<String>

    fun execute() : String

    fun loadAddressBook(path: String): Command {
        this.addressBook = FileParser.loadAddressBook(path)
        return this
    }

    fun loadAddressBook(addressBook: AddressBook): Command {
        this.addressBook = addressBook
        return this
    }

    fun withArgs(args: Array<String>): Command {
        this.args = args
        return this
    }
}