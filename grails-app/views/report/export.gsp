<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>
  
  <body>
  <center>
    <g:form action="exportPDF">
      <table div class="tableExport">
        <tr><td>
      <g:message code="report.export.client"/>
      <g:select name="clientInstance" from="${client}" optionKey="id" optionValue="label"/>
      </td></tr>
          <tr><td><g:message code="report.export.selectDate"/></td></tr>
          <tr><td>
            <g:radio name="date" value="month" checked="true"/><g:message code="report.export.month"/><br>
            <g:message code="report.export.month"/>:<g:select name="monthMonth" from="${nameMonth}" optionKey="id" optionValue="name"/>
            <g:message code="report.export.year"/>:<g:select name="yearMonth" from="${fYear..lYear}"/>
          </td></tr>
          <tr><td>
            <g:radio name="date" value="week"/><g:message code="report.export.week"/><br>
            <g:message code="report.export.week"/>:<g:select name="weekWeek" from="${1..numberWeeks}"/>
            <g:message code="report.export.year"/>:<g:select name="yearWeek" from="${fYear..lYear}"/>
          </td></tr>
      </table>
        
      <div class="buttons">
        <span class="button"><center><g:submitButton name="export" class="save" value="${message(code: 'report.submit', default: 'Create')}" /></center></span>
      </div>
    </g:form>
  </center>
  </body>
</html>
