package commands

import models.AddressBook

class DeleteCommand() : Command {
    override lateinit var addressBook: AddressBook
    override lateinit var args: Array<String>


    override fun execute(): String {
        if(args.size != 3) {
            return "Wrong arguments passed. Format: Delete FirstName LastName"
        }
        addressBook.removeContact(args[1], args[2])

        return "Contact removed"
    }
}