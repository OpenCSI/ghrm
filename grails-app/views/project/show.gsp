
<%@ page import="com.opencsi.ghrm.domain.*" %>
<%@ page import="com.opencsi.ghrm.services.*" %>


<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2><g:message code="global.project.information"/></h2>
  <g:render template="/common/projectInfo" />
  <br/>
  <g:render template="/common/projectTasks" model="['editMode': true]"/>
</body>
</html>