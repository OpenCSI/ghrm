package com.opencsi.ghrm.domain

class User {

    String uid
    String firstname
    String lastname
    String email
    String password
    String name
    String initials

    String toString() { "$uid"}

    static constraints = {
        email(email:true)
        password(blank:false, password:true)
    }

    public String getName() {
        return firstname.capitalize() + " " + lastname.capitalize()
    }

    public String getInitials() {
        return (firstname[0] + lastname[0] + lastname[1]).toUpperCase()
    }
}
