import com.opencsi.ghrm.domain.*
import com.opencsi.security.ShiroUser
import com.opencsi.security.ShiroRole
import org.apache.shiro.crypto.hash.*
import org.joda.time.DateTime

class BootStrap {

    def init = { servletContext ->
        def admin = new ShiroRole(name: 'admin')
        admin.addToPermissions("*:*")
        admin.save(failOnError: true)

        def projectLeader = new ShiroRole(name: 'projectleader')
        projectLeader.addToPermissions("customer:*")
        projectLeader.addToPermissions("project:*")
        projectLeader.addToPermissions("task:*")
        projectLeader.save(failOnError:true)

        def employee = new ShiroRole(name: 'employee')
        employee.addToPermissions("report:*")
        employee.save(failOnError:true)


        new User(uid:"bruno", firstname:"Bruno", lastname:"Bonfils", email:"admin@opencsi.com").save(failOnError:true)
        new User(uid:"manager", firstname:"John", lastname:"Doe", email:"doe@opencsi.com").save(failOnError:true)
        new User(uid:"brian", firstname: "Brian", lastname:"Jones", email:"brian@opencsi.com").save(failOnError:true)
        new User(uid:"james", firstname: "James", lastname:"Smith", email:"james@opencsi.com").save(failOnError:true)

        def bruno = new ShiroUser(username: 'bruno', passwordHash: new Sha256Hash("secret").toHex())
        bruno.addToRoles(ShiroRole.findByName('admin'))
        bruno.addToRoles(ShiroRole.findByName('projectleader'))
        bruno.addToRoles(ShiroRole.findByName('employee'))
        bruno.save(failOnError:true)

        def ced = new ShiroUser(username:'cedric',passwordHash: new Sha256Hash("cedric").toHex())
        ced.addToRoles(ShiroRole.findByName('admin'))
        ced.save(failOnError:true)


        def manager = new ShiroUser(username: 'manager', passwordHash: new Sha256Hash("secret").toHex())
        manager.addToRoles(ShiroRole.findByName('projectleader'))
        manager.addToRoles(ShiroRole.findByName('employee'))
        manager.save(failOnError:true)

        def brian = new ShiroUser(username: 'brian', passwordHash: new Sha256Hash("secret").toHex())
        brian.addToRoles(ShiroRole.findByName('employee'))
        brian.save(failOnError:true)

        def james = new ShiroUser(username: 'james', passwordHash: new Sha256Hash("secret").toHex())
        james.addToRoles(ShiroRole.findByName('employee'))
        james.save(failOnError:true)

        new Customer(name:"A big french company", street:"12 rue de la soif", city:"Paris", postalCode: "75000").save(failOnError:true)
        new Customer(name:"A small french company", street:"42 rue dtc", city:"Paris", postalCode: "75000").save(failOnError:true)

        new Project(name:"Project 1", customer: Customer.get(1),code: "C00101",description: "Assistance technique OpenAM",label: "1000",updateat: new Date(),createat: new Date()).save(failOnError:true)
        new Project(name:"Project 2", customer: Customer.get(2),code: "C00201",description: "Conseil securite",label: "1001",updateat: new Date(),createat: new Date()).save(failOnError:true)

        new Task(name:"Assistance technique", label:"AT").save(failOnError:true)
        new Task(name:"Project leader", label:"CP").save(failOnError:true)

        new TaskInstance(project: Project.get(1), task: Task.findByLabel("AT"), user: User.get(3), hours: 40).save(failOnError:true)
        new TaskInstance(project: Project.get(1), task: Task.findByLabel("CP"), user: User.get(4), hours: 4).save(failOnError:true)
        new TaskInstance(project: Project.get(2), task: Task.findByLabel("AT"), user: User.get(4), hours: 10).save(failOnError:true)

        def today = new DateTime()

        new TaskReport(taskInstance: TaskInstance.get(1), date: today.toDate(), hours: 4).save(failOnError:true)
        new TaskReport(taskInstance: TaskInstance.get(2), date: today.toDate(), hours: 4).save(failOnError:true)
        new TaskReport(taskInstance: TaskInstance.get(1), date: today.plusDays(1).toDate(), hours: 4).save(failOnError:true)
        new TaskReport(taskInstance: TaskInstance.get(1), date: today.minusDays(7).toDate(), hours: 4).save(failOnError:true)
        new TaskReport(taskInstance: TaskInstance.get(3), date: today.minusMonths(1).toDate(), hours: 8).save(failOnError:true)
        new TaskReport(taskInstance: TaskInstance.get(3), date: (today.minusMonths(1)).plusDays(1).toDate(), hours: 4).save(failOnError:true)
        new TaskReport(taskInstance: TaskInstance.get(2), date: today.plusDays(3).toDate(), hours: 4).save(failOnError:true)

        new TestObject(name: "toto",
        updateat: new Date(),
        createat: new Date()
        ).save(failOnError:true)
    }
    
    def destroy = {
    }
}
