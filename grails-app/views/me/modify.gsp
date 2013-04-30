<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="opencsi">
  </head>
  <body>
    <h2>${message(code: 'user.modify.title')}</h2>
    <g:form action="modify">
    <table>
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
        <th><g:message code="user.showIDLE"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'showIDLE', 'errors')}">
      <g:checkBox name="showIDLE" checked="${showIDLE}"/></td>
      </tr>
    </table>
    <center>
      <g:submitButton name="modify" value="${message(code: 'user.modify.href', default: 'Modify')}"/>
    </center>
    </g:form>
  </body>
</html>
