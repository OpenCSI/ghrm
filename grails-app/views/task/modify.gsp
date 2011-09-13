
<%@ page import="com.opencsi.ghrm.domain.*" %>
<%@ page import="com.opencsi.ghrm.services.*" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Modify task</h2>

<%@ page import="com.opencsi.ghrm.services.*" %>
  <g:hasErrors bean="${taskInstance}">
    <div class="errors">
      <g:renderErrors bean="${taskInstance}" as="list" />
    </div>
  </g:hasErrors>

  <g:form action="modify">
    <input type="hidden" name="id" value="${id}"/>
    <table>
      <tr>
        <th><g:message code="global.name"/></th>
      <td class="value ${hasErrors(bean: taskInstance, field: 'name', 'errors')}">
      <g:textField name="name" value="${name}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.label"/></th>
      <td class="value ${hasErrors(bean: taskInstance, field: 'label', 'errors')}">
      <g:textField name="label" value="${label}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.description"/></th>
      <td class="value ${hasErrors(bean: taskInstance, field: 'description', 'errors')}">
      <g:textField name="description" value="${description}"/></td>
      </tr>
    </table>
    <g:submitButton name="modify" value="${message(code: 'default.button.modify.label', default: 'Modify')}"/>
  </g:form>
</body>
</html>