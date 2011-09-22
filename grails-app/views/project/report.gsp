
<html>
  <head>
    <meta name="layout" content="opencsi" />
  </head>

  <body>
    <h2>Project report</h2>
    <g:render template="/common/calendarMonth" />
    <div class='export'>
      <span class='menuButton'>
        <g:link class="excel" controller="project" action="reportPDF" params="[
                  id:projectId,
                  year: currentYear,
                  month: currentMonth,format:'excel',extension:'xls'
                  ]">EXCEL</g:link>
      </span>
      <span class='menuButton'>
        <g:link class="pdf" controller="project" action="reportPDF" params="[
                  id:projectId,
                  year: currentYear,
                  month: currentMonth,format:'pdf',extension:'pdf'
                  ]">PDF</g:link>
      </span>
      <span class='menuButton'>
        <g:link class="xml" controller="project" action="reportPDF" params="[
                  id:projectId,
                  year: currentYear,
                  month: currentMonth,format:'xml',extension:'xml'
                  ]">XML</g:link>
      </span>
    </div>
  </body>
</html>