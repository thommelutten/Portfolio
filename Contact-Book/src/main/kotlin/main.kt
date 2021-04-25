import commands.ParseCommand
import models.AddressBook
import util.FileParser

fun main(args: Array<String>) {
    try {
        var command = ParseCommand.parse(args)
        command.loadAddressBook("addressBook.json")
        val output = command.withArgs(args).execute()

        println("\u001B[31m $output \u001B[0m")
        val saveOutput = command.saveAddressBook()
        println(saveOutput)

    } catch (e: Exception) {
        println(e.message)
    }

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    //println("\u001B[31m" + "Hello World!" + "\u001B[0m")
}