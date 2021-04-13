package models


class Contact (firstName: String, lastName: String, email: String = "", phone: String = "") {
    var firstName = firstName
        set(name) {
            require(
                name.isNotEmpty() &&
                        (name.filter { !it.isLetter() }).isEmpty()
            ) { "First name can not be empty and must only contain letters" }
            field = name
        }
    var lastName = lastName
        set(name) {
            require(
                name.isNotEmpty() &&
                        (name.filter { !it.isLetter() }).isEmpty()
            ) { "Last name can not be empty and must only contain letters"}
            field = name
        }
    var email = email
        set(email) {
            require(
                if(email.isNotEmpty()) { email.contains('@') } else { true }
            ) { "Email must contain an @"}
            field = email
        }
    var phone = phone
        set(phone) {
            require(
                (phone.filter { !it.isDigit() } ).isEmpty()
            ) { "Phone number must only contain digits"}
            field = phone
        }
    init {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.phone = phone
    }
}