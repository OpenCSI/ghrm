package com.opencsi.ghrm.domain

class Customer {

    String name
    String street
    String postalCode
    String city 
    byte[] picture
    String typePicture
    
    static constraints = {
        name(blank:false,nullable:false)
        street(blank:true, nullable:true)
        postalCode(blank:true, nullable:true)
        city(blank:true, nullable:true)
        picture(maxSize: 50000000)
    }
}
