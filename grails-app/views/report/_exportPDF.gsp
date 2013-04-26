<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <title>PDF</title>
  </head>
  <body>
  <center><h1>${client}</h1></center>
  <center>${date}</center>
  <g:each in="${list}">
    <table>
      <tr><td><b>${it.projectName}</b>:</td></tr>
      <tr><td><ul>
      <g:each in="${it.data}" var="value">
        <li>${value.getKey().getMonth()}/${value.getKey().getDate()}/${value.getKey().getYear() + 1900}: ${value.getValue()}</li>
      </g:each>
      </ul></td></tr>
      <tr><td><b>Total:</b> ${it.total} / ${it.max}.</td></tr>
    </table>
  </g:each>
  </body>
</html>
