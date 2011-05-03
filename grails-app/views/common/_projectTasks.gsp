<%@ page contentType="text/html;charset=UTF-8" %>

<table>
  <thead>
  <th><g:message code="global.user" /></th>
<th><g:message code="global.task"/></th>
<th><g:message code="global.hours.assigned"/></th>
<th><g:message code="global.hours.used"/></th>
</thead>
<tbody>
<g:each var="entry" in="${projectTasks}">
  <tr>
    <td>${entry.user.name}</td>
    <td>${entry.task.name}</td>
    <td>${entry.hours}</td>
    <td>${entry.hoursUsed}</td>
  </tr>
</g:each>
</tbody>
</table>