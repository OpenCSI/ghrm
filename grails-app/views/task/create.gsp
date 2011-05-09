
<%@ page import="com.opencsi.ghrm.domain.*" %>
<%@ page import="com.opencsi.ghrm.services.*" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Create task</h2>

<%@ page import="com.opencsi.ghrm.services.*" %>
  <g:hasErrors bean="${taskInstance}">
    <div class="errors">
      <g:renderErrors bean="${taskInstance}" as="list" />
    </div>
  </g:hasErrors>

  <g:form action="save">
    <table>
      <tr>
        <th><g:message code="global.name"/></th>
      <td class="value ${hasErrors(bean: taskInstance, field: 'name', 'errors')}">
      <g:textField name="name" value="${taskInstance?.name}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.label"/></th>
      <td class="value ${hasErrors(bean: taskInstance, field: 'label', 'errors')}">
      <g:textField name="label" value="${taskInstance?.label}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.description"/></th>
      <td class="value ${hasErrors(bean: taskInstance, field: 'description', 'errors')}">
      <g:textField name="description" value="${taskInstance?.description}"/></td>
      </tr>
    </table>
    <g:submitButton name="create" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
  </g:form>
</body>
</html>