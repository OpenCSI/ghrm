package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Recruitment
import com.opencsi.ghrm.domain.StatutRecruitment
import com.opencsi.ghrm.domain.MessageRecruitment
import com.opencsi.ghrm.domain.User

import com.opencsi.ghrm.services.MailService
import com.opencsi.ghrm.services.UserService

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.joda.time.DateTime

class HRController {
    def exportService
    UserService userService

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
         def recruitmentAccepted = Recruitment.findAllByStatut(StatutRecruitment.get(5),params)
         def recruitmentRefused = Recruitment.findAllByStatut(StatutRecruitment.get(4),params)
         
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
            flash.message = "Error cannot find the recruitment."
            redirect(action:'recruitment')
        }
    }
    def modify = {
        try
        {
            // get the current date:
            def today = new DateTime()
            // get the current recruitment (id):
            def recruitment = Recruitment.get(params.id)
            MailService mail = new MailService()
            // witch type of post method has been sent:
            if (params.statut)
            {
                // modify the statut:
                recruitment.statut = StatutRecruitment.findByName(params.statut)
                recruitment.updateat = today.toDate()
                recruitment.save()
                // send a email to the user:
                def content = "Dear " + recruitment.who + "\n\nYour statut has been modify to : " + params.statut + "."
                flash.message = mail.sendMail(recruitment.who,"[GHRM] : Recruitment - Statut",content) + "<br>Statut changed!"
            }
            else if( (params.title) && (params.content) )
            {
                // Send a email to the user:
                flash.message = mail.sendMail(recruitment.who,"[GHRM] : Recruitment : " + params.title,params.content + "\n\n" + User.findByUid(userService.getAuthenticatedUserName()).name)
                if (!flash.message =~ /Error/)
                {
                    new MessageRecruitment(title: params.title,
                                    message: params.content,createat: today.toDate(),
                                    recruitment: recruitment,who: User.findByUid(userService.getAuthenticatedUserName()).name).save(failOnError:true)
                    recruitment.updateat = today.toDate()
                    recruitment.save()
                }
            }
        }catch(Exception e)
        {
            flash.message = "Error to modify the data"
        }
        redirect(action:'recruitment')
    }
}
