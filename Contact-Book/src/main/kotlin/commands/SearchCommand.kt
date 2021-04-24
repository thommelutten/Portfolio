package commands

import models.AddressBook
import java.lang.StringBuilder

class SearchCommand() : Command {
    override lateinit var addressBook: AddressBook
    override lateinit var args: Map<String,List<String>>

    override fun execute(): String {
        val contactValues = args.entries.first().value
        if(contactValues.size < 2) {
            return "Wrong arguments passed. Format: Search Name"
        }

        val stringBuilder = StringBuilder()

        addressBook.searchContacts(contactValues[1]).forEach {
            stringBuilder.appendLine(it.firstName + " " + it.lastName + "\t\t" + it.email + "\t\t" + it.phone)
        }

        return stringBuilder.toString()
    }
}