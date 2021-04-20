package commands

import models.AddressBook

class ParseCommand {
    companion object {
        fun parse(args: Array<String>): Command {
            if (args.isEmpty()) {
                throw Exception("No arguments passed")
            }
            val command = CommandTypes.valueOf(args[0])

            return when (command.name) {
                "List" -> {
                    ListCommand()
                }
                "Add" -> {
                    AddCommand()
                }
                "Search" -> {
                    SearchCommand()
                }
                "Delete" -> {
                    DeleteCommand()
                }
                else -> throw Exception("Unknown Command")
            }
        }
    }
}

enum class CommandTypes() {
    List, Add, Search, Delete
}