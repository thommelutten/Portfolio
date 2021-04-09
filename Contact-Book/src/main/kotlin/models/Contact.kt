package models

class Contact (val firstName: String, val lastName: String, val email: String = "", val phone: String = "") {
    init {
        require(
            (firstName.filter { !it.isLetter() }).isEmpty()
        ) { "Firstname must only contain letters"}
        require(
            (lastName.filter { !it.isLetter() }).isEmpty()
        ) { "Lastname must only contain letters"}
        require(
            email.contains('@')
        ) { "Email must contain an @"}
        require(
            (phone.filter { !it.isDigit() } ).isEmpty()
        ) { "Phone number must only contain digits"}
    }
}