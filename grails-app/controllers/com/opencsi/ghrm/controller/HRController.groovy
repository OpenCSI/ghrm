package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Recruitment
import com.opencsi.ghrm.domain.StatutRecruitment
import com.opencsi.ghrm.domain.MessageRecruitment

import com.opencsi.ghrm.services.MailService

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
         
         def recruitmentNew = Recruitment.findAllByStatut(StatutRecruitment.get(1),params)
         def recruitmentProgress = Recruitment.findAllByStatut(StatutRecruitment.get(2),params)
         def recruitmentInterview = Recruitment.findAllByStatut(StatutRecruitment.get(3),params)
         def recruitmentAccepted = Recruitment.findAllByStatut(StatutRecruitment.get(4),params)
         def recruitmentRefused = Recruitment.findAllByStatut(StatutRecruitment.get(5),params)
         
        [recruitmentInstanceNewList: recruitmentNew,recruitmentInstanceProgressList: recruitmentProgress,
         recruitmentInstanceInterviewList : recruitmentInterview, recruitmentInstanceAcceptedList : recruitmentAccepted,
         recruitmentInstanceRefusedList: recruitmentRefused]
    }

    def more = {
        try
        {
            def recruitment = Recruitment.get(params.id)
            def message = MessageRecruitment.findAllByRecruitment(recruitment)
            [recruitment: recruitment, message: message, statut: StatutRecruitment.list()]
        }
        catch(Exception e)
        {
            flash.message = "Error" + e
            redirect(action:'recruitment')
        }
    }
    def modify = {
        try
        {
            // get the current recruitment (id):
            def recruitment = Recruitment.get(params.id)
            MailService mail = new MailService()
            // witch type of post method has been sent:
            if (params.statut)
            {
                // TODO
                flash.message = "HOP"
                // send a email to the user:
                def content = "Dear " + recruitment.who + "\n\nYour statut has been modify to : " + params.statut + "."
                mail.sendMail(recruitment.who,"[GHRM] : Recruitment",content)
            }
            else if( (params.titre) && (params.content) )
            {
                // TODO
                new MessageRecruitment(titre: params.titre,
                                message: params.content,createat: today.toDate(),
                                recruitment: recruitment,who: me).save(failOnError:true)
                // Send a email to the user:
                flash.message = "Recruitment mis à jour."
            }
        }catch(Exception e)
        {
            flash.message = "Error to modify the data"
        }
        redirect(action:'recruitment')
    }
}
