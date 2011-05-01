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

    static belongsTo = User
    static hasMany = [managers: User]
    static final Integer PROJECT_STATUS_OPEN = 0
    static final Integer PROJECT_STATUS_CLOSE = 64
    
    static constraints = {
        name(blank:false)
        label(nullable:false, blank: false)
        updateat(blank: true, nullable: true)
        createat(blank: true, nullable: true)
        code(nullable: true)
        description(nullable: true)
    }

    def beforeInsert = {
        createat = new Date()
    }

    def beforeUpdate = {
        updateat = new Date()
    }

}
