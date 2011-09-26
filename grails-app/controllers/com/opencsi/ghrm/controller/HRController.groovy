package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Recruitment
import com.opencsi.ghrm.domain.StatutRecruitment

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class HRController {
    def exportService

    def index = { }

    def recruitment = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
         // Export :
         if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=HR.Recruitment.${params.extension}")
            List fields = ['id','who','title','statut.name','createat']
            Map labels = ['id' : 'ID','who' : 'Who', 'title' : 'Title','statut.name': 'Statut','createat':'Date']
            exportService.export(params.format, response.outputStream,Recruitment.list(),fields,labels, [:], [:])
         }
         def recruitmentNew = Recruitment.findAllByStatut(StatutRecruitment.get(1))
         def recruitmentProgress = Recruitment.findAllByStatut(StatutRecruitment.get(2))
         def recruitmentInterview = Recruitment.findAllByStatut(StatutRecruitment.get(3))
         def recruitmentAccepted = Recruitment.findAllByStatut(StatutRecruitment.get(4))
         def recruitmentRefused = Recruitment.findAllByStatut(StatutRecruitment.get(5))
        [recruitmentInstanceNewList: recruitmentNew,recruitmentInstanceProgressList: recruitmentProgress,
         recruitmentInstanceInterviewList : recruitmentInterview, recruitmentInstanceAcceptedList : recruitmentAccepted,
         recruitmentInstanceRefusedList: recruitmentRefused]
    }

    def more = {
        try
        {
            def recruitment = Recruitment.get(params.id)
            [recruitment: recruitment]
            
        }
        catch(Exception e)
        {
            flash.message = "Error"
            redirect(action:'recruitment')
        }
    }
}
