<div class="calendar">
  <div class="calendarNavigation">
    <span class="previousDate">
      <g:link action="report" params="[
              id:projectId,
              year: monthInfos['previousYear'],
              month: monthInfos['previousMonth']
              ]">&lt;- <g:message code="default.paginate.prev" /></g:link>
    </span>
    <span class="nextDate">
      <g:link action="report" params="[
              id:projectId,
              year: monthInfos['nextYear'],
              month: monthInfos['nextMonth']
              ]"><g:message code="default.paginate.next" /> -&gt;</g:link>
    </span>
  </div>
  <table class="calendarTable">
    <thead>
      <tr><center>${nameMonth[currentMonth - 1 ]} ${currentYear}</center></tr>
      <tr><th>Lundi</th><th>Mardi</th><th>Mercredi</th><th>Jeudi</th><th>Vendredi</th><th>Samedi</th><th>Dimanche</th></tr>
    </thead>

    <tbody>
      <tr>
        <!-- Add emptys entries for previous month -->
    <g:each var="currentDay" in="${0..< monthInfos['firstDayOfWeek'] - 1}">
      <td></td>
    </g:each>
    <g:each var="currentDay" in="${0..< monthInfos['numberOfDays']}">
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
  </g:if>
</g:each>
</tr>
</tbody>
</table>
</div>
