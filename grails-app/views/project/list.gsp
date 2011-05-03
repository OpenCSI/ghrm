
<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>
  <body>
    <h2><g:message code="global.project.list" /></h2>
    <div class="body">
      <div class="list">
        <table>
          <thead>
            <tr>
          <g:sortableColumn property="name" title="${message(code: 'global.name')}" />
          <g:sortableColumn property="label" title="${message(code: 'global.label')}" />
          <g:sortableColumn property="customer.name" title="${message(code: 'global.customer')}" />
          <th><g:message code="default.actions"/></th>
          </tr>
          </thead>
          <tbody>
          <g:each in="${projectInstanceList}" status="i" var="projectInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: projectInstance, field: "name")}</td>
              <td>${fieldValue(bean: projectInstance, field: "label")}</td>
              <td>${fieldValue(bean: projectInstance, field: "customer.name")}</td>
              <td class="action"><g:link action="show" id="${projectInstance.id}">Show</g:link>
              <g:link action="report" id="${projectInstance.id}">Calendrier</g:link></td>
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
