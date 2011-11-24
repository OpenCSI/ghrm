<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="opencsi" />
  </head>
  <body>
    <h2>${message(code: 'user.modify.password.title')}</h2>
    <g:form action="password">
    <table>
      <tr>
        <th><g:message code="user.password.old"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'name', 'errors')}">
      <g:passwordField name="oldpassword" /></td>
      </tr>
      <tr><th/><td/></tr>
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
    </table>
    <center>
      <g:submitButton name="modify" value="${message(code: 'user.modify.href', default: 'Modify')}"/>
    </center>
    </g:form>
  </body>
</html>
