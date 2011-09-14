
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h1>New customer</h1>
    <%@ page import="com.opencsi.ghrm.services.*" %>
  <g:hasErrors bean="${customerInstance}">
    <div class="errors">
      <g:renderErrors bean="${customerInstance}" as="list" />
    </div>
  </g:hasErrors>
  
  <g:form action="save">
    <table>
      <thead>
      </thead>
      <tbody>
        <tr>
          <td>Name</td>
          <td class="value ${hasErrors(bean: customerInstance, field: 'name', 'errors')}">
            <input type="text" name="name" value="" />
          </td>
        </tr>
      </tbody>
    </table>
    <div class="buttons">
      <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
    </div>
  </g:form>
</body>
</html>
