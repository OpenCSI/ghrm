
<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <html>
    <head>
      <meta name="layout" content="opencsi" />
    </head>

    <body>
      <h2><g:message code="global.project.list" /></h2>
      <div class="body">
        <g:if test="${flash.message}">
          <div class="message">${flash.message}</div>
        </g:if>
        <div class="list">
          <table>
            <thead>
              <tr>

            <g:sortableColumn property="id" title="${message(code: 'project.id.label', default: 'Id')}" />

            <g:sortableColumn property="name" title="${message(code: 'project.name.label', default: 'Name')}" />

            <g:sortableColumn property="createat" title="${message(code: 'project.createat.label', default: 'Createat')}" />

            <th><g:message code="project.customer.label" default="Customer" /></th>

            <g:sortableColumn property="updateat" title="${message(code: 'project.updateat.label', default: 'Updateat')}" />

            </tr>
            </thead>
            <tbody>
            <g:each in="${projectInstanceList}" status="i" var="projectInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="report" id="${projectInstance.id}">${fieldValue(bean: projectInstance, field: "id")}</g:link></td>

              <td>${fieldValue(bean: projectInstance, field: "name")}</td>

              <td><g:formatDate date="${projectInstance.createat}" /></td>

              <td>${fieldValue(bean: projectInstance, field: "customer")}</td>

              <td><g:formatDate date="${projectInstance.updateat}" /></td>

              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="paginateButtons">
          <g:paginate total="${ProjectInstanceTotal}" />
        </div>
      </div>
    </body>
  </html>
