<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <title>PDF</title>
  </head>
  <body>
    <h1>${client}</h1>
  <g:each in="${list}">
    <b>${it.projectName}</b>:<br/><br/>
    <table>
    <g:each in="${it.data}" var="value">
      <tr><td>${value.getKey().getMonth()}/${value.getKey().getDate()}/${value.getKey().getYear() + 1900}:</td><td>${value.getValue()}</td></tr>
      <tr><td></td></tr>
    </g:each>
    </table>
    <b>Total:</b> ${it.total} / ${it.max}.<br/> <br/><br/>
  </g:each>
  </body>
</html>
