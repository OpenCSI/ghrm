
<%@ page contentType="text/html;charset=UTF-8" %>

<table>
  <thead>
  <th><g:message code="global.project" /></th>
  <th><g:message code="global.label"/></th>
  <th><g:message code="global.code"/></th>
  <th><g:message code="global.description"/></th>
  </thead>
  <tbody>
  <td>${project.name}</td>
  <td>${project.label}</td>
  <td>${project.code}</td>
  <td>${project.description}</td>
  </tbody>
</table>