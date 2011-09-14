
<%@ page import="com.opencsi.ghrm.domain.*" %>
<%@ page import="com.opencsi.ghrm.services.*" %>

<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>My report</h2>

<%@ page import="com.opencsi.ghrm.services.*" %>
  <g:form action="save">
    <g:hiddenField name="firstDate.Year" value="${weekInfos[0]['year']}"/>
    <g:hiddenField name="firstDate.Month" value="${weekInfos[0]['month']}"/>
    <g:hiddenField name="firstDate.Day" value="${weekInfos[0]['day']}"/>
    <div class="calendar">
      <div class="calendarNavigation">
        <span class="previousDate">
          <g:link controller="report" action="create" params="[
                  year: weekInfos[0]['year'],
                  month: weekInfos[0]['month'],
                  day: weekInfos[0]['day']
                  ]">&lt;- <g:message code="default.paginate.prev" /></g:link>
        </span>
        <span class="nextDate">
          <g:link controller="report"  action="create" params="[
                  year: weekInfos[8]['year'],
                  month: weekInfos[8]['month'],
                  day: weekInfos[8]['day']
                  ]"> <g:message code="default.paginate.next" /> -&gt;</g:link>
        </span>
        <br>
      </div>
      <table class="reportTable">
        <thead>
          <tr>
            <th style="background-color: white;"/>
        <g:set var="nameOfDays" value="${new CalendarService().getNameOfDays()}" />
        <g:each in="${1..7}" var="i">
          <th>${nameOfDays[i]} <br/> ${weekInfos[i]['day']} <g:message code="month.${weekInfos[i]['month']}"/> ${weekInfos[i]['year']}</th>
        </g:each>
        </tr>
        </thead>
        <tbody>
          <tr>
            <td><g:select name="taskInstance" optionKey="id" optionValue="label" from="${taskSelectOptions}"/></td>
        <g:each var="dayOfWeek" in="${1..7}">
          <td>
            <g:set var="currentDate" value="${dayOfWeek}"/>
          <g:textField name="${dayOfWeek}" id="dayOfWeek" value="0" />
          </td>
        </g:each>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
    </div>
  </g:form>
</body>
</html>