package com.opencsi.ghrm.domain

class Task {

    String name
    String label
    String description
    Date createat
    Date updateat

    static constraints = {
        description(nullable: true)
        label(nullable: true)
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
