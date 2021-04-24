package commands

import models.AddressBook

class DeleteCommand() : Command {
    override lateinit var addressBook: AddressBook
    override lateinit var args: Map<String,List<String>>


    override fun execute(): String {
        val contactValues = args.entries.first().value
        if(contactValues.size != 3) {
            return "Wrong arguments passed. Format: Delete FirstName LastName"
        }

        addressBook.removeContact(contactValues[1], contactValues[2])

        return "Contact removed"
    }
}