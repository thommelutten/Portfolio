package commands

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParseCommandTest {
    @Test
    fun parseCommandWithNoArgsTest() {
        val exception = Assertions.assertThrows(Exception::class.java) {
            ParseCommand.parse(arrayOf())
        }
        Assertions.assertEquals("No arguments passed", exception.message)
    }

    @Test
    fun parseCommandWithUnknownCommandTest() {
        val exception = Assertions.assertThrows(Exception::class.java) {
            ParseCommand.parse(arrayOf("SomeWierdCommand"))
        }
        Assertions.assertEquals("Unknown Command", exception.message)
    }

    @Test
    fun parseListCommandTest() {
        val command = ParseCommand.parse(arrayOf("List"))
        Assertions.assertEquals(ListCommand::class.java, command.javaClass)
    }

    @Test
    fun parseAddCommandTest() {
        val command = ParseCommand.parse(arrayOf("Add"))
        Assertions.assertEquals(AddCommand::class.java, command.javaClass)
    }

    @Test
    fun parseSearchCommandTest() {
        val command = ParseCommand.parse(arrayOf("Search"))
        Assertions.assertEquals(SearchCommand::class.java, command.javaClass)
    }

    @Test
    fun parseDeleteCommand() {
        val command = ParseCommand.parse(arrayOf("Delete"))
        Assertions.assertEquals(DeleteCommand::class.java, command.javaClass)
    }
}