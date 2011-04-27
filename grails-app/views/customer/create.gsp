
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h1>New customer</h1>
  <g:form action="save">
    <table>
      <thead>
      </thead>
      <tbody>
        <tr>
          <td>Name</td>
          <td>
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
