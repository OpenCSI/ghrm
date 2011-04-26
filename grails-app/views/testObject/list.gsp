
<%@ page import="com.opencsi.ghrm.domain.TestObject" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'testObject.label', default: 'TestObject')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'testObject.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="createat" title="${message(code: 'testObject.createat.label', default: 'Createat')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'testObject.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="updateat" title="${message(code: 'testObject.updateat.label', default: 'Updateat')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${testObjectInstanceList}" status="i" var="testObjectInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${testObjectInstance.id}">${fieldValue(bean: testObjectInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${testObjectInstance.createat}" /></td>
                        
                            <td>${fieldValue(bean: testObjectInstance, field: "name")}</td>
                        
                            <td><g:formatDate date="${testObjectInstance.updateat}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${testObjectInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
