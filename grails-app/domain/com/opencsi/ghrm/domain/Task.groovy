package com.opencsi.ghrm.domain

class Task {

    String name
    String label
    String description
    Date createat
    Date updateat

    static constraints = {
        name(nullable: false, blank: false)
        label(unique: true, blank: false)
        description(nullable: true)
        updateat(nullable: true)
        createat(nullable: true)
    }

    def beforeInsert = {
        createat = new Date()
    }

    def beforeUpdate = {
        updateat = new Date()
    }
}
