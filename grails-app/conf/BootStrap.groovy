import com.opencsi.ghrm.domain.*

class BootStrap {

    def init = { servletContext ->
        new User(uid:"bbonfils",
            firstname:"Bruno",
            lastname:"Bonfils",
            email:"bbonfils@opencsi.com",
            password:"toto"
        ).save(failOnError:true)

        new Customer(name:"Compagnie",
            street:"12 rue de la soif",
            city:"Paris", postalCode: "75000"
        ).save(failOnError:true)

        new Project(name:"Test",
            customer: Customer.findByName("Compagnie"),
            updateat: new Date(),
            createat: new Date()
        ).save(failOnError:true)

        new UserTask(name:"Conseil",
            project: Project.findByName("Test"),
            user: User.findByUid("bbonfils"),
            hours: 40
        ).save(failOnError:true)

        new UserTask(name:"Chef de projet",
            project: Project.findByName("Test"),
            user: User.findByUid("bbonfils"),
            hours: 10
        ).save(failOnError:true)

        new TaskReport(task: UserTask.findByName("Chef de projet"),
            date: new Date(),
            hours: 4
        ).save(failOnError:true)

        new TaskReport(task: UserTask.findByName("Conseil"),
            date: new Date(),
            hours: 4
        ).save(failOnError:true)

        new TaskReport(task: UserTask.findByName("Conseil"),
            date: new Date() - 30,
            hours: 4
        ).save(failOnError:true)

        new TestObject(name: "toto",
            updateat: new Date(),
            createat: new Date()
        ).save(failOnError:true)
    }
    
    def destroy = {
    }
}
