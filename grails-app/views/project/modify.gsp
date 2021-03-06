<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="opencsi">
  </head>
  <body>
    <h1><g:message code="project.modify.title"/></h1>
    <g:form action="modify">
    <input type="hidden" name="id" value ="${id}"/>
    <table>
      <tr>
        <th><g:message code="project.name"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'name', 'errors')}">
      <g:textField name="name" value="${name}"/></td>
      </tr>

      <tr>
        <th><g:message code="global.label"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'label', 'errors')}">
      <g:textField name="label" value="${label}"/></td>
      </tr>

      <tr>
        <th><g:message code="global.description"/></th>
      <td class="value ${hasErrors(bean: userInstance, field: 'description', 'errors')}">
      <textarea name="description" rows="5" cols="35">${description}</textarea>
      </tr>

      <tr>
        <th><g:message code="global.status"/></th>
        <td class="value ${hasErrors(bean: taskInstance, field: 'status', 'errors')}">
        <g:checkBox name="status" value="0" checked="${state}"/></td>
      </tr>
      
       <tr>
        <th><g:message code="global.color"/></th>
        <td class="value ${hasErrors(bean: taskInstance, field: 'color', 'errors')}">
          <div class="control-group">
             <div class="controls">
              <div class="input-prepend">
                 <span class="add-on"><img src="${resource(dir:'images',file:'adjust.png')}"/></span>
                  <g:textField name="color" class="span2 color" style="height:28px" value="${color}"/>
              </div>
             </div>
          </div>
        </td>
      </tr>
      
    </table>
    <center>
      <g:submitButton name="modify" value="${message(code: 'default.button.modify.label', default: 'modify')}"/>
    </center>
    </g:form>
  
    <script type="text/javascript" src="${resource(dir:'js/jscolor',file:'jscolor.js')}"></script>
  </body>
</html>
