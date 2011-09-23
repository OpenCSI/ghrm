package com.opencsi.ghrm.domain

class MessageRecruitment {
    String message
    Date createat
    User user
    
    static constraints = {
        message(nullable:false)
    }
}
