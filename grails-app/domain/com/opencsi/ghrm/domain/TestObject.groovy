package com.opencsi.ghrm.domain

class TestObject {

    String name
    Date createat
    Date updateat

    static constraints = {
    }

    def beforeInsert = {
        createat = new Date()
    }

    def beforeUpdate = {
        updateat = new Date()
    }
}
