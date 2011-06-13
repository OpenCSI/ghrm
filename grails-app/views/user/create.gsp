<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Create task</h2>

<%@ page import="com.opencsi.ghrm.services.*" %>
  <g:hasErrors bean="${userInstance}">
    <div class="errors">
      <g:renderErrors bean="${userInstance}" as="list" />
    </div>
  </g:hasErrors>

  <g:form action="save">
    <table>
      <tr>
        <th><g:message code="user.uid"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'uid', 'errors')}">
      <g:textField name="uid" value="${userInstance?.uid}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.firstname"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'firstname', 'errors')}">
      <g:textField name="firstname" value="${userInstance?.firstname}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.lastname"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'lastname', 'errors')}">
      <g:textField name="lastname" value="${userInstance?.lastname}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.email"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'email', 'errors')}">
      <g:textField name="email" value="${userInstance?.email}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.password"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'name', 'errors')}">
      <g:passwordField name="password" /></td>
      </tr>

      <tr>
        <th><g:message code="user.repassword"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'name', 'errors')}">
      <g:passwordField name="repassword" /></td>
      </tr>

      <tr>
        <th><g:message code="user.role"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'role', 'errors')}">
        <g:select name="role" from="${com.opencsi.security.ShiroRole.findAll()}" optionKey="name" optionValue="name" value="employee" />
      </td>
      </tr>

      <g:submitButton name="create" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
  </g:form>
</body>
</html>