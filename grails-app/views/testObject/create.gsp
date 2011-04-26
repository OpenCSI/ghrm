

<%@ page import="com.opencsi.ghrm.domain.TestObject" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'testObject.label', default: 'TestObject')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${testObjectInstance}">
            <div class="errors">
                <g:renderErrors bean="${testObjectInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createat"><g:message code="testObject.createat.label" default="Createat" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testObjectInstance, field: 'createat', 'errors')}">
                                    <g:datePicker name="createat" precision="day" value="${testObjectInstance?.createat}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="testObject.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testObjectInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${testObjectInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="updateat"><g:message code="testObject.updateat.label" default="Updateat" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testObjectInstance, field: 'updateat', 'errors')}">
                                    <g:datePicker name="updateat" precision="day" value="${testObjectInstance?.updateat}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
