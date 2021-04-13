package commands

import models.AddressBook

class AddCommand() : Command {

    override lateinit var addressBook: AddressBook
    override lateinit var args: Array<String>

    override fun execute(): String {
        if(args.size != 5) {
            return "Wrong arguments passed. Format: Add FirstName LastName Phone Email"
        }

        addressBook.addContact(firstName = args[1],
            lastName = args[2],
            phone = args[3],
            email = args[4]
        )
        return "Contact added"
    }
}