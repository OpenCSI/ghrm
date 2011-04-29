package com.opencsi.ghrm.domain

class Project {

    String name
    Date createat
    Date updateat
    Customer customer
    String description
    String code
    String label
    Integer status = PROJECT_STATUS_OPEN

    static final Integer PROJECT_STATUS_OPEN = 0
    static final Integer PROJECT_STATUS_CLOSE = 64
    
    static constraints = {
        name(blank:false)
        updateat(blank: true)
        createat(blank: true)
    }

    def beforeInsert = {
        createat = new Date()
    }

    def beforeUpdate = {
        updateat = new Date()
    }

}
