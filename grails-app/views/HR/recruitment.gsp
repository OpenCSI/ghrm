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
    <h2><g:message code="global.recruitment.list" /></h2>
        <div class="list">
          <table>
            <thead>
            <center><h5>NEWS:</h5></center>
              <tr>
            <g:sortableColumn property="id" title="${message(code: 'recruitment.id', default: 'id')}" />
            <g:sortableColumn property="who" title="${message(code: 'recruitment.who', default: 'Who')}" />
            <g:sortableColumn property="title" title="${message(code: 'recruitment.title', default: 'Title')}" />
            <g:sortableColumn property="statut" title="${message(code: 'recruitment.statut', default: 'Statut')}" />
            <g:sortableColumn property="file" title="${message(code: 'recruitment.file', default: 'File')}" />
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${recruitmentInstanceNewList}" status="i" var="recruitmentInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: recruitmentInstance, field: "id")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "who")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "title")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "statut.name")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "file")}</td>
              <td class="action" style="text-align: justify;">
                <g:link action="more" id="${recruitmentInstance.id}"><g:message code="recruitment.more.href"/></g:link>
              </td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="list">
          <table>
            <thead>
            <center><h5>In Progress:</h5></center>
              <tr>
            <g:sortableColumn property="id" title="${message(code: 'recruitment.id', default: 'id')}" />
            <g:sortableColumn property="who" title="${message(code: 'recruitment.who', default: 'Who')}" />
            <g:sortableColumn property="title" title="${message(code: 'recruitment.title', default: 'Title')}" />
            <g:sortableColumn property="statut" title="${message(code: 'recruitment.statut', default: 'Statut')}" />
            <g:sortableColumn property="file" title="${message(code: 'recruitment.file', default: 'File')}" />
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${recruitmentInstanceProgressList}" status="i" var="recruitmentInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: recruitmentInstance, field: "id")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "who")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "title")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "statut.name")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "file")}</td>
              <td class="action" style="text-align: justify;">
                <g:link action="more" id="${recruitmentInstance.id}"><g:message code="recruitment.more.href"/></g:link>
              </td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="list">
          <table>
            <thead>
            <center><h5>Interview:</h5></center>
              <tr>
            <g:sortableColumn property="id" title="${message(code: 'recruitment.id', default: 'id')}" />
            <g:sortableColumn property="who" title="${message(code: 'recruitment.who', default: 'Who')}" />
            <g:sortableColumn property="title" title="${message(code: 'recruitment.title', default: 'Title')}" />
            <g:sortableColumn property="statut" title="${message(code: 'recruitment.statut', default: 'Statut')}" />
            <g:sortableColumn property="file" title="${message(code: 'recruitment.file', default: 'File')}" />
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${recruitmentInstanceInterviewList}" status="i" var="recruitmentInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: recruitmentInstance, field: "id")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "who")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "title")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "statut.name")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "file")}</td>
              <td class="action" style="text-align: justify;">
                <g:link action="more" id="${recruitmentInstance.id}"><g:message code="recruitment.more.href"/></g:link>
              </td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="list">
          <table>
            <thead>
            <center><h5>Accepted:</h5></center>
              <tr>
            <g:sortableColumn property="id" title="${message(code: 'recruitment.id', default: 'id')}" />
            <g:sortableColumn property="who" title="${message(code: 'recruitment.who', default: 'Who')}" />
            <g:sortableColumn property="title" title="${message(code: 'recruitment.title', default: 'Title')}" />
            <g:sortableColumn property="statut" title="${message(code: 'recruitment.statut', default: 'Statut')}" />
            <g:sortableColumn property="file" title="${message(code: 'recruitment.file', default: 'File')}" />
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${recruitmentInstanceAcceptedList}" status="i" var="recruitmentInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: recruitmentInstance, field: "id")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "who")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "title")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "statut.name")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "file")}</td>
              <td class="action" style="text-align: justify;">
                <g:link action="more" id="${recruitmentInstance.id}"><g:message code="recruitment.more.href"/></g:link>
              </td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
        <div class="list">
          <table>
            <thead>
            <center><h5>Refused:</h5></center>
              <tr>
            <g:sortableColumn property="id" title="${message(code: 'recruitment.id', default: 'id')}" />
            <g:sortableColumn property="who" title="${message(code: 'recruitment.who', default: 'Who')}" />
            <g:sortableColumn property="title" title="${message(code: 'recruitment.title', default: 'Title')}" />
            <g:sortableColumn property="statut" title="${message(code: 'recruitment.statut', default: 'Statut')}" />
            <g:sortableColumn property="file" title="${message(code: 'recruitment.file', default: 'File')}" />
            <th><g:message code="default.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${recruitmentInstanceRefusedList}" status="i" var="recruitmentInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: recruitmentInstance, field: "id")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "who")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "title")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "statut.name")}</td>
              <td>${fieldValue(bean: recruitmentInstance, field: "file")}</td>
              <td class="action" style="text-align: justify;">
                <g:link action="more" id="${recruitmentInstance.id}"><g:message code="recruitment.more.href"/></g:link>
              </td>
              </tr>
            </g:each>
            </tbody>
          </table>
        </div>
      <export:formats formats="['excel', 'pdf', 'xml']"/>
      <br>
  </body>
</html>
