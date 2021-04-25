package commands

import models.AddressBook
import models.Contact
import java.lang.Exception

class UpdateCommand() : Command {
    override lateinit var addressBook: AddressBook
    override lateinit var args: Map<String, List<String>>
    override lateinit var addressBookPath: String
    private lateinit var contact: Contact

    override fun execute(): String {
        if(args.keys.size < 2) {
            return "Wrong arguments passed. Format: Update FirstName LastName [-FirstName -LastName -Email -Phone]"
        }


        args.forEach {
            when (it.key.toLowerCase()) {
                "" -> {
                    try {
                        contact = addressBook.findContact("${it.value[1]} ${it.value[2]}")
                    } catch (e: Exception) {
                        return "Unable to update. No such contact found"
                    }
                }
                "-firstname" -> {
                    try {
                        contact.firstName = it.value[0]
                    } catch (e: Exception) {
                        return "Unable to update. Invalid firstname"
                    }

                }
                "-lastname" -> {
                    try {
                        contact.lastName = it.value[0]
                    } catch (e: Exception) {
                        return "Unable to update. Invalid lastname"
                    }
                }
                "-email" -> {
                    try {
                        contact.email = it.value[0]
                    } catch (e: Exception) {
                        return "Unable to update. Invalid email"
                    }
                }
                "-phone" -> {
                    try {
                        contact.phone = it.value[0]
                    } catch (e: Exception) {
                        return "Unable to update. Invalid phone"
                    }
                }
            }
            addressBook.updateContact(contact)
        }
        return "Updated Contact"
    }
}