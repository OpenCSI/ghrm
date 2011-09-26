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
    ${fieldValue(bean: recruitment, field: "id")}
    ${fieldValue(bean: recruitment, field: "statut.name")}
    ${fieldValue(bean: recruitment, field: "title")}
    ${fieldValue(bean: recruitment, field: "comment")}
    ${fieldValue(bean: recruitment, field: "who")}
  </body>
</html>
