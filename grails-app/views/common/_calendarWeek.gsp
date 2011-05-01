
<%@ page import="com.opencsi.ghrm.services.*" %>

<div class="calendar">
  <div class="calendarNavigation">
    <span class="previousDate">
      <g:link controller="report" action="week" params="[
              year: weekInfos[0]['year'],
              month: weekInfos[0]['month'],
              day: weekInfos[0]['day']
              ]">&lt;- <g:message code="default.paginate.prev" /></g:link>
    </span>
    <span class="nextDate">
      <g:link controller="report"  action="week" params="[
              year: weekInfos[8]['year'],
              month: weekInfos[8]['month'],
              day: weekInfos[8]['day']
              ]"> <g:message code="default.paginate.next" /> -&gt;</g:link>
    </span>

  </div>
  <table class="reportTable">
    <thead>
      <tr>
        <th style="background-color: white;"/>
    <g:set var="nameOfDays" value="${new CalendarService().getNameOfDays()}" />
    <g:each in="${1..7}" var="i">
      <th>${nameOfDays[i]} <br/> ${weekInfos[i]['day']} <g:message code="month.${weekInfos[i]['month']}"/></th>
    </g:each>
    </tr>
    </thead>

    <tbody>
      <tr>
    <g:each var="dayOfWeek" in="${1..7}">
      <td>
      <g:if test="${calendarData.containsKey(dayOfWeek)}">
        <g:each var="entry" in="${calendarData[dayOfWeek]}">
${entry}
        </g:each>
      </g:if>        
      </td>
    </g:each>
    </tr>
    </tbody>
  </table>
</div>
