
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
    <br>
  </div>
  <table class="reportTable">
    <thead>
      <tr>
     <g:set var="nameOfDays" value="${new CalendarService().getNameOfDays()}" />
     <g:each in="${1..7}" var="i">
      <th>${nameOfDays[i]} <br/> ${weekInfos[i]['day']} <g:message code="month.${weekInfos[i]['month']}"/>
      ${weekInfos[i]['year']}</th>
     </g:each>
     </tr>
    </thead>

    <tbody>
      <tr>
    <g:each var="dayOfWeek" in="${1..7}">
      <td>
      <g:if test="${calendarData.containsKey(dayOfWeek)}">
        <g:each status="i" var="entry" in="${calendarData[dayOfWeek]}">
          <g:set var="tooltip" value="${entry['tooltipdata']}" />
          <div class="calendarData" onmouseover="tooltip.show('${tooltip}')" onmouseout="tooltip.hide()">
${entry['htmldata']}
          </div>
          <modalbox:createLink controller="report" action="confirm" id="${entry.id}" title="${message(code:'confirm.title', default: 'Attention')}" width="250">Delete</modalbox:createLink>
          ${}
        </g:each>
      </g:if>        
      </td>
    </g:each>
    </tr>
    </tbody>
  </table>
</div>
