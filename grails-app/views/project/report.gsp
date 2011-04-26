
<%@ page import="com.opencsi.ghrm.domain.*" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project report</title>
  </head>
  <body>

    <div class="secondary">
      <span class="menuButton"><a class="home" href="/ghrm/">Home</a></span>
      <span class="menuButton"><a href="/ghrm/customer/create" class="create">New Customer</a></span>
    </div>

    <h1>Summary report for project ${project.name}</h1>
    <div class="list">
      <table>
        <thead>
          <tr><th>Task</th> <th>User</th> <th>Hours</th> </tr>
        </thead>
        <tbody>
        <g:each in="${tasks}" status="i" var="task">
          <tr>
            <td>${task.name}</td>
            <td>${task.user.name}</td>
            <td>${task.hours}</td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
  </body>
</html>