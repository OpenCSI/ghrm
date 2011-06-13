package com.opencsi.ghrm.domain
import com.opencsi.ghrm.domain.Project

class User {

    String uid
    String firstname
    String lastname
    String email
    //    String name
    //    String initials

    String toString() { "$uid"}

    static hasMany = [ projectsManaged: Project]

    static constraints = {
        email(email:true, nullable: false, blank: false)
        initials(blank: true, nullable: true)
        name(blank: true, nullable: true)
        uid(nullable: false, blank: false, unique: true)
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
    }

    public setName(String name) { }
    public setInitials(String initials) { }
        
    public String getName() {
        return firstname.capitalize() + " " + lastname.capitalize()
    }

    public String getInitials() {
        if (firstname.length() > 0 && lastname.length() >0) {
            return (firstname[0] + lastname[0] + lastname[1]).toUpperCase()
        } else {
            return null
        }
    }
}
