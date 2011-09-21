package ghrm


class TestWebTests extends grails.util.WebTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

   void testSomething() {
       // index:
        invoke '/'
        // login:
        setInputField(name:'username',value:'bruno')
        setInputField(name:'password',value:'secret')
        clickButton(label : 'Sign in')
        // English language:
        invoke '?lang=en'
        // test the menu:
        clickLink(label :'current week')
        clickLink(label: 'New CRA')
        clickLink(label: 'New')
        clickLink(label: 'List')
        clickLink(label: 'Add a task')
        clickLink(label: 'List tasks')
        clickLink(label: 'Add a employee')
        clickLink(label: 'List employees')
    }

}