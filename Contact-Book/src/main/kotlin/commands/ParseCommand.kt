package commands

import models.AddressBook

class ParseCommand {
    fun parse(args: Array<String>): Command {
        if(args.isEmpty()) {
            throw Exception("No arguments passed")
        }
        val command = CommandTypes.valueOf(args[0])

        when(command.name) {
            "ListCommand" -> {
                println("Listing contacts")
                return ListCommand(AddressBook()) // TODO Update to content from file instead
            }
            else -> throw Exception("Unknown Command")
        }
    }
}

enum class CommandTypes() {
    ListCommand
}