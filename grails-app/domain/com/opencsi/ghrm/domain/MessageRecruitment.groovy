package com.opencsi.ghrm.domain

class MessageRecruitment {
    String titre
    String message
    Date createat
    Recruitment recruitment
    String who
    
    static constraints = {
        message(nullable:false)
    }

    static mapping = {
       sort createat:'desc'
   }
}
