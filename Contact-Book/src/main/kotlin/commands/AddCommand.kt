package commands

import models.AddressBook

class AddCommand() : Command {
    override lateinit var addressBook: AddressBook
    override lateinit var args: Map<String,List<String>>

    override fun execute(): String {
        val contactValues = args.entries.first().value
        if(contactValues.size != 5) {
            return "Wrong arguments passed. Format: Add FirstName LastName Phone Email"
        }

        addressBook.addContact(firstName = contactValues[1],
            lastName = contactValues[2],
            phone = contactValues[3],
            email = contactValues[4]
        )
        return "Contact added"
    }
}