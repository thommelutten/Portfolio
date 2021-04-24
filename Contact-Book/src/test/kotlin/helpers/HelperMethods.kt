package helpers

import models.AddressBook
import models.Contact

public fun createAddressBookWithContacts(): AddressBook {
    return AddressBook(mutableListOf(
        Contact("Simon", "Says", "test@test.com", "55443322"),
        Contact("Thor", "Odinsson", "thor@asgard.com", "11223344"),
        Contact("Odin", "Allfather", "odin@asgard.com", "66554433")
    ))
}