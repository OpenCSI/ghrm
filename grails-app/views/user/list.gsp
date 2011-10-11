
<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <html>
    <head>
      <meta name="layout" content="opencsi" />
    </head>

    <body>
      <h2><g:message code="global.user.list" /></h2>
        <div class="list">
          <table>
            <thead>
              <tr>
            <g:sortableColumn property="id" title="${message(code: 'project.id', default: 'id')}" />
            <g:sortableColumn property="name" title="${message(code: 'project.name', default: 'Name')}" />
            <g:sortableColumn property="email" title="${message(code: 'project.email', default: 'email')}" />
            <g:sortableColumn property="uid" title="${message(code: 'project.uid', default: 'uid')}" />
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${userInstanceList}" status="i" var="userInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: userInstance, field: "id")}</td>
              <td>${fieldValue(bean: userInstance, field: "name")}</td>
              <td>${fieldValue(bean: userInstance, field: "email")}</td>
              <td>${fieldValue(bean: userInstance, field: "uid")}</td>
              <td class="action" style="text-align: justify;"><g:link action="modify" id="${userInstance.id}"><g:message code="user.modify.href"/></g:link>
              <modalbox:createLink controller="user" action="confirm" id="${userInstance.id}" title="${message(code:'confirm.title', default: 'Attention')}"  width="250"><g:message code="user.delete.href"/></modalbox:createLink></td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="paginateButtons">
          <g:paginate total="${userInstanceTotal}" />
        </div>
      <auth:isAdmin>
    <div class="middle">
        <a href="${createLink(controller:"user", action:"create")}" class="btn large primary"><g:message code="global.new"/></a>
      </div>
  </auth:isAdmin>
      <export:formats formats="['excel', 'pdf', 'xml']"/>
    </body>
  </html>
