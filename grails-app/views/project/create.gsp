
<%@ page import="com.opencsi.ghrm.domain.*" %>
<%@ page import="com.opencsi.ghrm.services.*" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Create project</h2>

<%@ page import="com.opencsi.ghrm.services.*" %>
  <g:hasErrors bean="${projectInstance}">
    <div class="errors">
      <g:renderErrors bean="${projectInstance}" as="list" />
    </div>
  </g:hasErrors>

  <g:form action="save">
    <table>
      <tr>
        <th><g:message code="global.name"/></th>
      <td class="value ${hasErrors(bean: projectInstance, field: 'name', 'errors')}">
      <g:textField name="name" value="${projectInstance?.name}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.customer"/></th>
      <td class="value ${hasErrors(bean: projectInstance, field: 'customer', 'errors')}">
      <g:select name="customer" from="${Customer.findAll()}" optionKey="id" optionValue="name" value="${projectInstance?.customer?.id}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.label"/></th>
      <td class="value ${hasErrors(bean: projectInstance, field: 'label', 'errors')}">
      <g:textField name="label" value="${projectInstance?.label}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.code"/></th>
      <td class="value ${hasErrors(bean: projectInstance, field: 'code', 'errors')}">
      <g:textField name="code" value="${projectInstance?.code}"/></td>
      </tr>
      <tr>
        <th><g:message code="global.description"/></th>
      <td class="value ${hasErrors(bean: projectInstance, field: 'description', 'errors')}">
      <g:textField name="description" value="${projectInstance?.description}" /></td>
      </tr>
    </table>
    <g:submitButton name="create" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
  </g:form>
</body>
</html>