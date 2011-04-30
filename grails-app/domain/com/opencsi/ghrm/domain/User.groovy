package com.opencsi.ghrm.domain
import com.opencsi.ghrm.domain.Project

class User {

    String uid
    String firstname
    String lastname
    String email
    String name
    String initials

    String toString() { "$uid"}

    static hasMany = [ projectsManaged: Project]

    static constraints = {
        email(email:true)
    }

    public String getName() {
        return firstname.capitalize() + " " + lastname.capitalize()
    }

    public String getInitials() {
        return (firstname[0] + lastname[0] + lastname[1]).toUpperCase()
    }
}
