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
        <th><g:message code="user.role.add"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'role', 'errors')}">
        <g:select multiple="multiple" name="role" from="${com.opencsi.security.ShiroRole.findAll()}" optionKey="name" optionValue="name" value="employee" />
      </td>
      </tr>
    </table>
    <center>
      <g:submitButton name="modify" value="${message(code: 'default.button.modify.label', default: 'modify')}"/>
    </center>
    </g:form>

    <table>
      <tr>
        <th><g:message code="user.role.del"/></th>
        <td>
         <g:each in="${roles.roles}" status="i" var="roleInstance">
          <g:form action="deleteRule">
            <input type="hidden" name="id" value ="${id}"/>
            <input type="hidden" name="rule" value ="${roleInstance.id}"/>
             <g:submitButton name="modify" value="${message(code: 'user.role.del.href')}"/> "${roleInstance.name}"<br>
           </g:form>
         </g:each>
        </td>
      </tr>
    </table>
</body>
</html>