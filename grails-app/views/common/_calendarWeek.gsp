
<%@ page import="com.opencsi.ghrm.services.*" %>

<div class="calendar">
  <div class="calendarNavigation">
    <span class="previousDate">
      <g:link controller="report" action="week" params="[
              year: weekInfos[0]['year'],
              month: weekInfos[0]['month'],
              day: weekInfos[0]['day']
              ]">&lt;- Previous</g:link>
    </span>
    <span class="nextDate">
      <g:link controller="report"  action="week" params="[
              year: weekInfos[8]['year'],
              month: weekInfos[8]['month'],
              day: weekInfos[8]['day']
              ]">Next -&gt;</g:link>
    </span>

  </div>
  <table class="calendarTable">
    <thead>
      <tr>
    <g:set var="nameOfDays" value="${new CalendarService().getNameOfDays()}" />
    <g:each in="${1..7}" var="i">
      <th>${nameOfDays[i]}</th>
    </g:each>
    </tr>
    </thead>
          <tr>
    <g:each var="currentDay" in="${1..7}">
      <td>
${weekInfos[currentDay]['year']} - ${weekInfos[currentDay]['month']} - ${weekInfos[currentDay]['day']}
      </td>
    </g:each>
    </tr>


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