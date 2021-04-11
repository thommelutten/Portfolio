package models

class Contact (firstName: String, lastName: String, email: String = "", phone: String = "") {
    init {
        require(
            firstName.isNotEmpty() &&
            (firstName.filter { !it.isLetter() }).isEmpty()
        ) { "First name can not be empty and must only contain letters"}
        require(
            lastName.isNotEmpty() &&
            (lastName.filter { !it.isLetter() }).isEmpty()
        ) { "Last name can not be empty and must only contain letters"}
        require(
             if(email.isNotEmpty()) {email.contains('@')} else {true}
        ) { "Email must contain an @"}
        require(
            (phone.filter { !it.isDigit() } ).isEmpty()
        ) { "Phone number must only contain digits"}
    }
    var firstName: String = firstName
        set(name) {
            require(
                (name.filter { !it.isLetter() }).isEmpty()
            ) { "First name must only contain letters"}
            field = name
        }
    var lastName: String = lastName
        set(name) {
            require(
                (name.filter { !it.isLetter() }).isEmpty()
            ) { "First name must only contain letters"}
            field = name
        }

    var email: String = email
        set(e) {
            require(
                email.isNotEmpty() && e.contains('@')
            ) { "Email must contain an @"}
            field = e
        }

    var phone: String = phone
        set(p) {
            require(
                (p.filter { !it.isDigit() } ).isEmpty()
            ) { "Phone number must only contain digits"}
            field = p
        }
}