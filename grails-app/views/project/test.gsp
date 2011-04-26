
<%@ page import="com.opencsi.ghrm.domain.*" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <title>Project report</title>
  </head>
  <body>

    <div class="secondary">
      <span class="menuButton"><a class="home" href="/ghrm/">Home</a></span>
      <span class="menuButton"><a href="/ghrm/customer/create" class="create">New Customer</a></span>
    </div>

    <h1>Calendar</h1>

    <div class="calendar">
      <div class="calendarNavigation">
        <span class="previousMonth">
          <g:link action="test" params="[
                  id:projectId,
                  year: monthInfos['previousYear'],
                  month: monthInfos['previousMonth']
                 ]">&lt;- ${default.paginate.prev}</g:link>
        </span>
        <span class="nextMonth">
          <g:link action="test" params="[
                  id:projectId,
                  year: monthInfos['nextYear'],
                  month: monthInfos['nextMonth']
                 ]">Next -&gt;</g:link>
        </span>
      </div>
      <table class="calendarTable">
        <thead>
          <tr><th>Lundi</th><th>Mardi</th><th>Mercredi</th><th>Jeudi</th><th>Vendredi</th><th>Samedi</th><th>Dimanche</th></tr>
        </thead>

        <tbody>

          <tr>
            <!-- Add emptys entries for previous month -->
        <g:each var="currentDayday" in="${0..< monthInfos['firstDayOfWeek'] - 1}">
          <td></td>
        </g:each>
        <g:each var="currentDay" in="${0..< monthInfos['numberOfDays']}">
          <td>
            <div class="numberOfDay">${currentDay + 1}</div>
            <div class="calendarData">
              <g:if test="${calendarData.containsKey(currentDay)}">
                <g:each var="entry" in="${calendarData[currentDay]}">
                   <%=entry%>
                </g:each>
              </g:if>
            </div>
          </td>
          <g:if test="${((currentDay + monthInfos['firstDayOfWeek'])) % 7 == 0}">
          </tr><tr>
      </g:if>
    </g:each>
    </tr>
</tbody>
</table>
</div>
</body>
</html>