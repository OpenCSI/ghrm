package com.opencsi.ghrm.domain

class Project {

    String name
    Date createat
    Date updateat
    Customer customer
    
    static constraints = {
        name(blank:false)
    }

    def beforeInsert = {
        createat = new Date()
    }

    def beforeUpdate = {
        updateat = new Date()
    }
}
