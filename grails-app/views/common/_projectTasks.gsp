<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.opencsi.ghrm.domain.*" %>

<table>
  <thead>
  <th><g:message code="global.user" /></th>
<th><g:message code="global.task"/></th>
<th><g:message code="global.days.assigned"/></th>
<th><g:message code="global.days.used"/></th>
</thead>
<tbody>
  <tr>
<g:form controller="taskInstance" action="save">
  <g:hiddenField name="projectid" value="${params.id}"/>
  <td><g:select name="userid" from="${User.findAll()}" optionKey="id" optionValue="name" value="${user?.uid}" /></td>
  <td><g:select name="taskid" from="${Task.findAll()}" optionKey="id" optionValue="name" value="${task?.customer?.id}" /></td>
  <td><g:textField name="days" value="0" /></td>
  <td><g:submitButton name="create" value="add"/></td>
</g:form>
</tr>
<g:each var="entry" in="${projectTasks}">
  <tr>
    <td>${entry.user.name}</td>
    <td>${entry.task.name}</td>
    <td>${entry.days}</td>
    <td>${entry.daysUsed}</td>
  </tr>
</g:each>
</tbody>
</table>