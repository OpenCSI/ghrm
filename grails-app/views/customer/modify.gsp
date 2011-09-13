
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h1>Modify a customer</h1>
  <g:form action="modify">
    <input type="hidden" name="id" value="${id}" />
    <table>
      <thead>
      </thead>
      <tbody>
        <tr>
          <td>Name</td>
          <td>
            <input type="text" name="name" value="${name}" />
          </td>
        <tr>
          <td>city</td>
          <td>
            <input type="text" name="city" value="${city}" />
          </td>
        </tr>
        <tr>
          <td>street</td>
          <td>
            <input type="text" name="street" value="${street}" />
          </td>
        </tr>
        <tr>
          <td>postal code</td>
          <td>
            <input type="text" name="postal_code" value="${postal_code}" />
          </td>
        </tr>
      </tbody>
    </table>
    <div class="buttons">
      <span class="button"><g:submitButton name="modify" class="save" value="${message(code: 'default.button.modify.label', default: 'Modify')}" /></span>
    </div>
  </g:form>
</body>
</html>
