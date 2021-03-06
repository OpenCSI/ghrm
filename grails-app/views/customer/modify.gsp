
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h1><g:message code="customer.modify.title"/></h1>
  <g:form action="modify" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${id}" />
    <table>
      <thead>
      </thead>
      <tbody>
        <tr>
          <td><g:message code="customer.name"/></td>
          <td>
            <input type="text" name="name" value="${name}" />
          </td>
        <tr>
          <td><g:message code="customer.city"/></td>
          <td>
            <input type="text" name="city" value="${city}" />
          </td>
        </tr>
        <tr>
          <td><g:message code="customer.street"/></td>
          <td>
            <input type="text" name="street" value="${street}" />
          </td>
        </tr>
        <tr>
          <td><g:message code="customer.postalcode"/></td>
          <td>
            <input type="text" name="postal_code" value="${postal_code}" />
          </td>
        </tr>
        <tr>
          <td>Upload Picture:</td>
          <td><input type="file" name="picture" /></td>
        </tr>
        <tr>
          <td colspan="2"><center><img src="data:${typePicture};base64,${picture}" alt="${name}"/></center></td>
        </tr>
      </tbody>
    </table>
    <div class="buttons">
      <span class="button"><g:submitButton name="modify" class="save" value="${message(code: 'customer.modify.submit', default: 'Modify')}" /></span>
    </div>
  </g:form>
</body>
</html>
