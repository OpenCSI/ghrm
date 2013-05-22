import com.opencsi.ghrm.domain.*
import com.opencsi.security.ShiroUser
import com.opencsi.security.ShiroRole
import org.apache.shiro.crypto.hash.Sha256Hash
import org.joda.time.DateTime
import grails.util.Environment

class BootStrap {
    def grailsApplication
    def quartzScheduler
    
    def init = { servletContext ->
        // JOB:
        // Test int configuration file, if the HRRecruitment job is enabled:
        def appConfig = grailsApplication.config
        if (appConfig.recruitment.status.toString().equals("stop"))
        {
            quartzScheduler.deleteJob('HRRecruitmentJob','HRRecruitment')
            println("[GHRM Init] : The HRRecruitment Job is stopped")
        }
        else if (appConfig.recruitment.status.toString().equals("start"))
        {
            println("[GHRM Init] : The HRRecruitment Job is started")
        }

        // Default value:
           def adminRole
           if (!(adminRole = ShiroRole.findByName('admin')))
                adminRole = new ShiroRole(name: 'admin')
           adminRole.addToPermissions("*:*")
           adminRole.save(flush:true)

            def projectLeader
            if (!(projectLeader = ShiroRole.findByName('projectLeader')))
                projectLeader = new ShiroRole(name: 'projectleader')
            projectLeader.addToPermissions("customer:*")
            projectLeader.addToPermissions("project:*")
            projectLeader.addToPermissions("task:*")
            projectLeader.addToPermissions("report:*")
            projectLeader.addToPermissions("taskInstance:*")
            projectLeader.addToPermissions("me:*")
            projectLeader.save(flush:true)

            def employee
            if (!(employee = ShiroRole.findByName('employee')))
                employee = new ShiroRole(name: 'employee')
            employee.addToPermissions("report:*")
            employee.addToPermissions("me:*")
            employee.save(flush:true)

            def HR
            if (!(HR = ShiroRole.findByName('HR')))
                HR = new ShiroRole(name : 'HR')
            HR.addToPermissions("HR:*")
            HR.save(flush:true)

            if (!User.findByUid('admin'))
            {
                new User(uid:"admin", firstname:"Admin", lastname:"Administrator", email:"admin@opencsi.com").save(failOnError:true)
                def admin = new ShiroUser(username: 'admin', passwordHash: new Sha256Hash("secret").toHex())
                admin.addToRoles(ShiroRole.findByName('admin'))
                admin.addToRoles(ShiroRole.findByName('projectleader'))
                admin.addToRoles(ShiroRole.findByName('employee'))
                admin.addToRoles(ShiroRole.findByName('HR'))
                admin.save()
            }

            // HR:
            if (!StatutRecruitment.findByName('New'))
                new StatutRecruitment(name :"New").save()
            if (!StatutRecruitment.findByName('In progress'))
                new StatutRecruitment(name :"In progress").save()
            if (!StatutRecruitment.findByName('Interview'))
                new StatutRecruitment(name :"Interview").save()
            if (!StatutRecruitment.findByName('Refused'))
                new StatutRecruitment(name :"Refused").save()
            if (!StatutRecruitment.findByName('Accepted'))
                new StatutRecruitment(name :"Accepted").save()
    }
    
    def destroy = {
    }
}
