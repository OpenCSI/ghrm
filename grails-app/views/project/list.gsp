
<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>
  <body>
    <h2><g:message code="global.project.list" />
      <g:if test="${actif == false}">
        <span class="hrefActif"><g:link action="list/actif"><g:message code="project.show.actif"/></g:link></span>
      </g:if>
      <g:else>
        <span class="hrefActif"><g:link action="list"><g:message code="project.show.inactif"/></g:link></span>
      </g:else></h2>
    <div class="body">
      <div class="list">
        <table>
          <thead>
            <tr>
          <g:sortableColumn property="name" title="${message(code: 'global.name')}" />
          <g:sortableColumn property="label" title="${message(code: 'global.label')}" />
          <g:sortableColumn property="customer.name" title="${message(code: 'global.customer')}" />
          <g:sortableColumn property="label" title="${message(code: 'global.description')}" />
          <th><g:message code="default.actions"/></th>
          </tr>
          </thead>
          <tbody>
          <g:each in="${projectInstanceList}" status="i" var="projectInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <g:if test="${projectInstance.status == Project.STATUS_OPEN}">
                <td>${fieldValue(bean: projectInstance, field: "name")}</td>
              </g:if>
              <g:else>
                <td style="color:#CECECE">${fieldValue(bean: projectInstance, field: "name")} </td>
              </g:else>
              <td>${fieldValue(bean: projectInstance, field: "label")}</td>
              <td>${fieldValue(bean: projectInstance, field: "customer.name")}</td>
              <td>${fieldValue(bean: projectInstance, field: "description")}</td>
              <td class="action" style="text-align: justify;"><g:link action="modify" id="${projectInstance.id}"><g:message code="project.modify.href"/></g:link>
                <g:link action="show" id="${projectInstance.id}"><g:message code="project.show.href"/></g:link>
                <g:link action="report" id="${projectInstance.id}"><g:message code="project.calendar.href"/></g:link>
              </td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
      <div class="paginateButtons">
        <g:paginate total="${ProjectInstanceTotal}" />
      </div>
      <auth:isProjectLeader>
        <div class="middle">
            <a href="${createLink(controller:"project", action:"create")}" class="btn large primary"><g:message code="global.new"/></a>
          </div>
      </auth:isProjectLeader>
      <export:formats formats="['excel', 'pdf', 'xml']"/>
    </div>
  </body>
</html>
