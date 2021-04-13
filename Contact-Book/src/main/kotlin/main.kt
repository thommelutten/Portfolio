import commands.ParseCommand
import models.AddressBook

fun main(args: Array<String>) {
    try {
        val command = ParseCommand.parse(args)
        command.loadAddressBook(AddressBook()).withArgs(args).execute()

    } catch (e: Exception) {
        println(e.message)
    }

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    println("\u001B[31m" + "Hello World!" + "\u001B[0m")
}