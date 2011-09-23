package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Recruitment

class HRController {

    def index = { }

    def recruitment = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
         // Export :
         /*if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=user.list.${params.extension}")
            List fields = ['id','name','email','uid']
            Map labels = ['id' : 'ID','name' : 'Name', 'email' : 'Email','uid': 'UID']
            exportService.export(params.format, response.outputStream,User.list(params),fields,labels, [:], [:])
         }*/
        [recruitmentInstanceList: Recruitment.list(params), recruitmentInstanceTotal: Recruitment.count()]
    }

    def more = {
        try
        {
            def recruitment = Recruitment.get(params.id)
            
        }
        catch(Exception e)
        {
            flash.message = "Error"
            redirect(action:'recruitment')
        }
    }
}
