import commands.ParseCommand
import models.AddressBook
import util.FileParser

fun main(args: Array<String>) {
    try {
        val command = ParseCommand.parse(args)
        val output = command.loadAddressBook("addressBook.json").withArgs(args).execute()
        println(output)

    } catch (e: Exception) {
        println(e.message)
    }

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    //println("\u001B[31m" + "Hello World!" + "\u001B[0m")
}