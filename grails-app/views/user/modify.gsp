<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Modify User</h2>

<%@ page import="com.opencsi.ghrm.services.*" %>
  <g:hasErrors bean="${userInstance}">
    <div class="errors">
      <g:renderErrors bean="${userInstance}" as="list" />
    </div>
  </g:hasErrors>

  <g:form action="modify">
    <input type="hidden" name="id" value ="${id}"/>
    <table>
      <tr>
        <th><g:message code="user.uid"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'uid', 'errors')}">
      <g:textField name="uid" value="${uid}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.firstname"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'firstname', 'errors')}">
      <g:textField name="firstname" value="${firstname}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.lastname"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'lastname', 'errors')}">
      <g:textField name="lastname" value="${lastname}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.email"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'email', 'errors')}">
      <g:textField name="email" value="${email}"/></td>
      </tr>

      <tr>
        <th><g:message code="user.role"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'role', 'errors')}">
        <g:select name="role" from="${com.opencsi.security.ShiroRole.findAll()}" optionKey="name" optionValue="name" value="employee" />
      </td>
      </tr>
    </table>
      <g:submitButton name="modify" value="${message(code: 'default.button.modify.label', default: 'modify')}"/>
  </g:form>
</body>
</html>