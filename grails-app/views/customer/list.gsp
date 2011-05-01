
<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <html>
    <head>
      <meta name="layout" content="opencsi" />
    </head>

    <body>
      <h2><g:message code="global.customer.list" /></h2>
        <div class="list">
          <table>
            <thead>
              <tr>
            <g:sortableColumn property="name" title="${message(code: 'project.name', default: 'Name')}" />
            </tr>
            </thead>
            <tbody>
            <g:each in="${customerInstanceList}" status="i" var="customerInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: customerInstance, field: "name")}</td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="paginateButtons">
          <g:paginate total="${customerInstanceTotal}" />
        </div>
      </div>
    </body>
  </html>
