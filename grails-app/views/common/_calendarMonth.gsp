<div class="calendar">
  <div class="calendarNavigation">
    <span class="previousDate">
      <g:if test="${value == 'report'}">
        <g:link action="month" params="[
                year: monthInfos['previousYear'],
                month: monthInfos['previousMonth']
                ]">&lt;- <g:message code="default.paginate.prev" /></g:link>
      </g:if>
      <g:if test="${value == 'project'}">
        <g:link action="report" params="[
                id:projectId,
                year: monthInfos['previousYear'],
                month: monthInfos['previousMonth']
                ]">&lt;- <g:message code="default.paginate.prev" /></g:link>
      </g:if>
    </span>
    <span class="nextDate">
      <g:if test="${value == 'report'}">
        <g:link action="month" params="[
                year: monthInfos['nextYear'],
                month: monthInfos['nextMonth']
                ]"><g:message code="default.paginate.next" /> -&gt;</g:link>
      </g:if>
      <g:if test="${value == 'project'}">
        <g:link action="report" params="[
                id:projectId,
                year: monthInfos['nextYear'],
                month: monthInfos['nextMonth']
                ]"><g:message code="default.paginate.next" /> -&gt;</g:link>
      </g:if>
    </span>
  </div>
  <g:set var="totalNumberOfDays" value="${0}" />
  <g:set var="totalNumberOfWeeks" value="${0}" />
  <table class="calendarTable">
    <thead>
      <tr><center>${nameMonth[currentMonth - 1 ]} ${currentYear}</center></tr>
    <tr><th><g:message code="day.1"/></th><th><g:message code="day.2"/></th><th><g:message code="day.3"/></th><th><g:message code="day.4"/></th><th><g:message code="day.5"/></th><th><g:message code="day.6"/></th><th><g:message code="day.7"/></th></tr>
    </thead>

    <tbody>
      <tr>
        <!-- Add emptys entries for previous month -->
    <g:each var="currentDay" in="${0..< monthInfos['firstDayOfWeek'] - 1}">
      <g:set var="totalNumberOfDays" value="${totalNumberOfDays + 1}"/>
      <td></td>
    </g:each>
    <g:each var="currentDay" in="${0..< monthInfos['numberOfDays']}">
      <g:set var="totalNumberOfDays" value="${totalNumberOfDays + 1}"/>
      <td>
        <div class="numberOfDay">${currentDay + 1}</div>
      <g:if test="${calendarData.containsKey(currentDay)}">
        <g:each var="entry" in="${calendarData[currentDay]}">
          <g:set var="tooltip" value="${entry['tooltipdata']}" />
          <div class="calendarData" onmouseover="tooltip.show('${tooltip}')" onmouseout="tooltip.hide()">
<%=entry['htmldata']%>
          </div>
        </g:each>
      </g:if>
      </td>
      <g:if test="${((currentDay + monthInfos['firstDayOfWeek'])) % 7 == 0}">
      </tr><tr>
        <g:set var="totalNumberOfWeeks" value="${totalNumberOfWeeks+1}" />
  </g:if>
</g:each>
    <!-- add empty cells to finish the week -->
    <g:each var="i" in="${(totalNumberOfDays..<(totalNumberOfWeeks+1)*7)}">
      <td></td>
    </g:each>
</tr>
</tbody>
</table>
</div>
