package commands

import models.AddressBook

class ParseCommand {
    companion object {
        fun parse(args: Array<String>): Command {
            if (args.isEmpty()) {
                throw Exception("No arguments passed")
            }

            val command = try{
                CommandTypes.valueOf(args[0].toUpperCase())
            } catch (e: Exception) {
                CommandTypes.UNKNOWN
            }


            return when (command.name) {
                "LIST" -> {
                    ListCommand()
                }
                "ADD" -> {
                    AddCommand()
                }
                "SEARCH" -> {
                    SearchCommand()
                }
                "DELETE" -> {
                    DeleteCommand()
                }
                "UPDATE" -> {
                    UpdateCommand()
                }
                else -> throw Exception("Unknown Command")
            }
        }
    }
}

enum class CommandTypes() {
    LIST, ADD, SEARCH, DELETE, UPDATE, UNKNOWN
}