package com.opencsi.ghrm.domain

class Recruitment {
    String who
    String title
    String comment
    String file
    StatutRecruitment statut
    User user
    Date createat
    Date updateat

    static hasMany = [message: MessageRecruitment]
    static constraints = {
        who(nullable: false, blank: false)
        title(nullable:false,blanke: false)
    }

    def beforeInsert = {
        createat = new Date()
    }

    def beforeUpdate = {
        updateat = new Date()
    }
}
