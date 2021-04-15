package commands

import models.AddressBook
import java.lang.StringBuilder

class SearchCommand() : Command {
    override lateinit var addressBook: AddressBook
    override lateinit var args: Array<String>

    override fun execute(): String {
        if(args.size < 2) {
            return "Wrong arguments passed. Format: Search Name"
        }

        val stringBuilder = StringBuilder()

        addressBook.searchContacts(args[1]).forEach {
            stringBuilder.appendLine(it.firstName + " " + it.lastName + "\t\t" + it.email + "\t\t" + it.phone)
        }

        return stringBuilder.toString()
    }
}