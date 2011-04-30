

<%@ page import="com.opencsi.ghrm.domain.Project" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
  <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>

<g:form action="save" >
  <div class="dialog">
    <table>
      <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="name"><g:message code="project.name.label" default="Name" /></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'name', 'errors')}">
      <g:textField name="name" value="${projectInstance?.name}" />
      </td>
      </tr>

      <tr class="prop">
        <td valign="top" class="name">
          <label for="customer"><g:message code="project.customer.label" default="Customer" /></label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'customer', 'errors')}">
      <g:select name="customer.id" from="${com.opencsi.ghrm.domain.Customer.list()}" optionKey="id" value="${projectInstance?.customer?.id}"  />
      </td>
      </tr>
      </tbody>
    </table>
  </div>
  <div class="buttons">
    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
  </div>
</g:form>
</html>
