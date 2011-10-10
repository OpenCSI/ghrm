
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
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${customerInstanceList}" status="i" var="customerInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: customerInstance, field: "name")}</td>
              <td class="action" style="text-align: justify;"><g:link action="modify" id="${customerInstance.id}"><g:message code="customer.modify.href"/></g:link>
              <modalbox:createLink controller="customer" action="confirm" id="${customerInstance.id}" title="${message(code:'confirm.title', default: 'Attention')}"  width="250"><g:message code="customer.delete.href"/></modalbox:createLink></td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="paginateButtons">
          <g:paginate total="${customerInstanceTotal}" />
        </div>

      <div class="middle">
        <a href="${createLink(controller:"customer", action:"create")}" class="btn large primary"><g:message code="global.customer.create"/></a>
      </div>
      <export:formats formats="['excel', 'pdf', 'xml']"/>
    </body>
  </html>
