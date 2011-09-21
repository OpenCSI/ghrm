
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Project report</h2>
    <g:render template="/common/calendarMonth" />
    <g:link controller="project" action="reportPDF" params="[
              id:projectId,
              year: currentYear,
              month: currentMonth
              ]">PDF</g:link>
  </body>
</html>