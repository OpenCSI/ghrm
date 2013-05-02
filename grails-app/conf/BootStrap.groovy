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
        // Init scenario:
        //if( (appConfig.dataSource.dbCreate == "create") || (appConfig.dataSource.dbCreate == "create-drop") )
        //{
            //  if (!ShiroRole.findByName('admin'))
           // {
                def adminRole = new ShiroRole(name: 'admin')
                adminRole.addToPermissions("*:*")
                adminRole.save(flush:true)
          // }

            //if (!ShiroRole.findByName('projectleader'))
           // {
                def projectLeader = new ShiroRole(name: 'projectleader')
                projectLeader.addToPermissions("customer:*")
                projectLeader.addToPermissions("project:*")
                projectLeader.addToPermissions("task:*")
                projectLeader.addToPermissions("report:*")
                projectLeader.addToPermissions("taskInstance:*")
                projectLeader.addToPermissions("me:*")
                projectLeader.save(flush:true)
            //}

           // if (!ShiroRole.findByName('employee'))
            //{
                def employee = new ShiroRole(name: 'employee')
                employee.addToPermissions("report:*")
                employee.addToPermissions("me:*")
                employee.save(flush:true)
            //}

           // if (!ShiroRole.findByName('HR'))
           // {
                def HR = new ShiroRole(name : 'HR')
                HR.addToPermissions("HR:*")
                HR.save(flush:true)
            //}

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
       // }
       /* if (Environment.current.name != 'production') {
            if (!User.findByUid('cedric'))
			{
				new User(uid:'cedric', firstname:'Cédric', lastname:'Joron', email:'un-autre-mail@opencsi.com').save(failOnError:true)
				def ced = new ShiroUser(username:'cedric',passwordHash: new Sha256Hash("cedric").toHex())
				ced.addToRoles(ShiroRole.findByName('employee'))
				ced.addToRoles(ShiroRole.findByName('HR'))
				ced.save(failOnError:true)
			}

			if (!User.findByUid('manager'))
			{
				new User(uid:"manager", firstname:"John", lastname:"Doe", email:"doe@opencsi.com").save(failOnError:true)
				def manager = new ShiroUser(username: 'manager', passwordHash: new Sha256Hash("secret").toHex())
				manager.addToRoles(ShiroRole.findByName('projectleader'))
				manager.addToRoles(ShiroRole.findByName('employee'))
				manager.save(failOnError:true)
			}

			if (!User.findByUid('brian'))
			{
				new User(uid:"brian", firstname: "Brian", lastname:"Jones", email:"brian@opencsi.com").save(failOnError:true)
				def brian = new ShiroUser(username: 'brian', passwordHash: new Sha256Hash("secret").toHex())
				brian.addToRoles(ShiroRole.findByName('employee'))
				brian.save(failOnError:true)
			}

			if (!User.findByUid('james'))
			{
				new User(uid:"james", firstname: "James", lastname:"Smith", email:"james@opencsi.com").save(failOnError:true)
				def james = new ShiroUser(username: 'james', passwordHash: new Sha256Hash("secret").toHex())
				james.addToRoles(ShiroRole.findByName('employee'))
				james.save(failOnError:true)
			}
			if(!Customer.findByName("A big french company"))
				new Customer(name:"A big french company", street:"12 rue de la soif", city:"Paris", postalCode: "75000").save(failOnError:true)
            if(!Customer.findByName("A small french company"))
				new Customer(name:"A small french company", street:"42 rue dtc", city:"Paris", postalCode: "75000").save(failOnError:true)

			if(!Project.findByLabel("1000"))
				new Project(name:"Project 1", customer: Customer.get(1),code: "C00101",description: "Assistance technique OpenAM",label: "1000",updateat: new Date(),createat: new Date(),color: "#ffc726").save(failOnError:true)
            if(!Project.findByLabel("1001"))
				new Project(name:"Project 2", customer: Customer.get(2),code: "C00201",description: "Conseil securite",label: "1001",updateat: new Date(),createat: new Date(),color: "#ff7300").save(failOnError:true)

			if(!Task.findByLabel("AT"))
				new Task(name:"Assistance technique", label:"AT").save(failOnError:true)
            if(!Task.findByLabel("CP"))
				new Task(name:"Project leader", label:"CP").save(failOnError:true)
			
			if(Task.findByLabel("AT"))
			{
				new TaskInstance(project: Project.get(1), task: Task.findByLabel("AT"), user: User.get(3), days: 40).save(failOnError:true)
				new TaskInstance(project: Project.get(2), task: Task.findByLabel("AT"), user: User.get(4), days: 10).save(failOnError:true)
			}
			if(Task.findByLabel("CP"))
				new TaskInstance(project: Project.get(1), task: Task.findByLabel("CP"), user: User.get(4), days: 4).save(failOnError:true)

            def today = new DateTime()
	
            new TaskReport(taskInstance: TaskInstance.get(1), date: today.toDate(), days: 1).save(failOnError:true)
            new TaskReport(taskInstance: TaskInstance.get(2), date: today.toDate(), days: 1).save(failOnError:true)
            new TaskReport(taskInstance: TaskInstance.get(1), date: today.plusDays(1).toDate(), days: 0.5).save(failOnError:true)
            new TaskReport(taskInstance: TaskInstance.get(1), date: today.minusDays(7).toDate(), days: 1.2).save(failOnError:true)
            new TaskReport(taskInstance: TaskInstance.get(3), date: today.minusMonths(1).toDate(), days: 1).save(failOnError:true)
            new TaskReport(taskInstance: TaskInstance.get(3), date: (today.minusMonths(1)).plusDays(1).toDate(), days: 1.1).save(failOnError:true)
            new TaskReport(taskInstance: TaskInstance.get(2), date: today.plusDays(3).toDate(), days: 0.9).save(failOnError:true)
            new Recruitment(who:"test@employee.me",title:"Post for Developer",comment:"hello, i am posting my candidature for the developer proposition.",
                file:"CV_test.pdf",statut:StatutRecruitment.get(1),
                user: User.get(1),createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
            new Recruitment(who:"test@employee.me",title:"Post for Developer",comment:"hello, i am posting my candidature for the developer proposition.",
                file:"CV_test.pdf",statut:StatutRecruitment.get(2),
                user: User.get(1),createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
            new Recruitment(who:"test@employee.me",title:"Post for Developer",comment:"hello, i am posting my candidature for the developer proposition.",
                file:"CV_test.pdf",statut:StatutRecruitment.get(3),
                user: User.get(1),createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
            new Recruitment(who:"test@employee.me",title:"Post for Developer",comment:"hello, i am posting my candidature for the developer proposition.",
                file:"CV_test.pdf",statut:StatutRecruitment.get(4),
                user: User.get(1),createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
            new Recruitment(who:"test@employee.me",title:"Post for Developer",comment:"hello, i am posting my candidature for the developer proposition.",
                file:"CV_test.pdf",statut:StatutRecruitment.get(5),
                user: User.get(1),createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
        }*/
    }
    
    def destroy = {
    }
}
