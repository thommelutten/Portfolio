package commands

import models.AddressBook

interface Command {
    var addressBook: AddressBook

    fun execute() : String
}