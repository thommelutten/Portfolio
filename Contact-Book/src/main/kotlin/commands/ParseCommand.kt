package commands

import models.AddressBook

class ParseCommand {
    companion object {
        fun parse(args: Array<String>): Command {
            if (args.isEmpty()) {
                throw Exception("No arguments passed")
            }

            val command = try{
                CommandTypes.valueOf(args[0])
            } catch (e: Exception) {
                CommandTypes.Unknown
            }


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
    List, Add, Search, Delete, Unknown
}