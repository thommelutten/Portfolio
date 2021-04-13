package commands

import models.AddressBook

interface Command {
    var addressBook: AddressBook
    var args: Array<String>

    fun execute() : String

    fun loadAddressBook(addressBook: AddressBook): Command {
        this.addressBook = addressBook
        return this
    }

    fun withArgs(args: Array<String>): Command {
        this.args = args
        return this
    }
}