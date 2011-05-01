<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>
  <body>
    <table id="task">
      <tr>
        <th>Name</th>
        <td>${task.name}</td>
      </tr>
      <tr>
        <th>Label</th>
        <td>${task.label}</td>
      </tr>
      <tr>
        <th>Description</th>
        <td>${task.description}</td>
      </tr>
    </table>
  </body>
</html>
