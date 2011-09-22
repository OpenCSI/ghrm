
<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <html>
    <head>
      <meta name="layout" content="opencsi" />
    </head>

    <body>
      <h2><g:message code="task.list" /></h2>
      <div class="list">
        <table>
          <thead>
            <tr>
          <g:sortableColumn property="name" title="${message(code: 'global.name', default: 'Name')}" />
          <g:sortableColumn property="label" title="${message(code: 'global.label', default: 'Label')}" />
          <th>${message(code: 'global.description')}</th>
          <th><g:message code="default.actions"/></th>
          </tr>
          </thead>
          <tbody>
          <g:each in="${taskInstanceList}" status="i" var="taskInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: taskInstance, field: "name")}</td>
              <td>${fieldValue(bean: taskInstance, field: "label")}</td>
              <td>${fieldValue(bean: taskInstance, field: "description")}</td>
              <td class="action" style="text-align: justify;"><g:link action="modify" id="${taskInstance.id}">
               <g:message code="task.modify.href"/></g:link></td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
      <div class="paginateButtons">
        <g:paginate total="${taskInstanceTotal}" />
      </div>
      <export:formats formats="['excel', 'pdf', 'xml']"/>
    </body>
  </html>
