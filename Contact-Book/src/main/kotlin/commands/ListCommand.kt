package commands

import models.AddressBook
import java.lang.StringBuilder

class ListCommand(override var addressBook: AddressBook) : Command {
    override fun execute(): String {
        if(addressBook.getContacts().isEmpty()) {
            return "Address Book is Empty"
        }
        val stringBuilder = StringBuilder()

        addressBook.getContacts().forEach {
            stringBuilder.appendLine(it.firstName + " " + it.lastName + "\t\t" + it.email + "\t\t" + it.phone)
        }
        return stringBuilder.toString()
    }

}