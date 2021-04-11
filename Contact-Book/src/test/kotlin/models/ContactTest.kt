package models

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class ContactTest {

    @Test
    fun createContactTest() {
        val c = Contact("Simon", "Says", "s@test.com", "55443322")
        Assertions.assertEquals("Simon", c.firstName)
        Assertions.assertEquals("Says", c.lastName)
        Assertions.assertEquals("s@test.com", c.email)
        Assertions.assertEquals("55443322", c.phone)
    }

    @Test
    fun createContactWithIllegalFirstNameTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Contact("S1mon", "Says","s@test.com", "55443322")
        }
        Assertions.assertEquals("First name can not be empty and must only contain letters", exception.message)
    }

    @Test
    fun createContactWithIllegalLastNameTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Contact("Simon", "S1ays","s@test.com", "55443322")
        }
        Assertions.assertEquals("Last name can not be empty and must only contain letters", exception.message)
    }

    @Test
    fun createContactWithIllegalEmailTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Contact("Simon", "Says","stest.com", "55443322")
        }
        Assertions.assertEquals("Email must contain an @", exception.message)
    }

    @Test
    fun createContactWithIllegalPhoneNumberTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Contact("Simon", "Says","s@test.com", "a55443322")
        }
        Assertions.assertEquals("Phone number must only contain digits", exception.message)
    }

    @Test
    fun createContactWithEmptyPhoneNumberTest() {
        val c = Contact("Simon", "Says", "s@test.com", "")
        Assertions.assertEquals("", c.phone)
    }

    @Test
    fun throwErrorWithEmptyFirstNameOrLastNameTest() {
        var exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Contact("", "Says","s@test.com", "a55443322")
        }
        Assertions.assertEquals("First name can not be empty and must only contain letters", exception.message)

        exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Contact("Simon", "","s@test.com", "a55443322")
        }

        Assertions.assertEquals("Last name can not be empty and must only contain letters", exception.message)

    }

    @Test
    fun updateContactWithNewUnacceptableValueTest() {
        val c = Contact("Simon", "Says","s@test.com", "55443322")

        Assertions.assertEquals("55443322", c.phone)

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            c.firstName = "S1mon"
        }
        Assertions.assertEquals("First name must only contain letters", exception.message)
        Assertions.assertEquals("Simon", c.firstName)
    }

    @Test
    fun updateContactWithAllowedValueTest() {
        val c = Contact("Simon", "Says","s@test.com", "55443322")

        Assertions.assertEquals("55443322", c.phone)

        c.phone = "11223344"

        Assertions.assertEquals("11223344", c.phone)
    }

}