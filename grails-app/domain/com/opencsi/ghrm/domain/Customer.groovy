package com.opencsi.ghrm.domain

class Customer {

    String name
    String street
    String postalCode
    String city

    static constraints = {
        name(blank:false)
        street(blank:true, nullable:true)
        postalCode(blank:true, nullable:true)
        city(blank:true, nullable:true)
    }
}
