<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <style type="text/css">
      @page {
        size: landscape;
      }
      
      table.reportTable {
        border-collapse:collapse;
        width: 100%;
      }
      table.reportTable thead tr th {
          border: 0.3px solid gray; 
          background: #fdf6e3;
          text-align: center;
      }

      table.reportTable tbody tr td {
          text-align: center;
          border: 0.3px solid gray; 
      }

      table.reportTable tbody tr td input {
          width: 40px;
          text-align: center;
      }
      
      .numberOfDay {
          background: #fdf6e3;
          text-align: center;
          font-style: italic;
      }
      
      .signature {
        float: right;
        font-weight:bold;
      }
      
      .picture {
        position:fixed;
        top: 0px;
      }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>PDF Month</title>
  </head>
  <body>
    <center><h1>${client}</h1></center>
    <center>${date}</center>
    <g:if test="${picture}">
      <span class="picture"><img src="data:${typePicture};base64,${picture}" alt="${name}"/></span>
    </g:if>
    <g:set var="totalNumberOfDays" value="${0}" />
    <g:set var="totalNumberOfWeeks" value="${0}" />
  
    <table class="reportTable">
      <thead>
        <tr>
        <g:each in="${1..7}" var="i">
          <th>
            <g:message code="day.${i}"/>
          </th>
         </g:each>
        </tr>
      </thead>
      <tbody>
        <tr>
         <g:each var="currentDay" in="${0..< monthInfos['firstDayOfWeek'] - 1}">
      <g:set var="totalNumberOfDays" value="${totalNumberOfDays + 1}"/>
      <td></td>
    </g:each>
    <g:each var="currentDay" in="${0..< monthInfos['numberOfDays']}">
      <g:set var="totalNumberOfDays" value="${totalNumberOfDays + 1}"/>
      <td valign="top">
        <div class="numberOfDay">${currentDay + 1}</div>
      <g:each in="${list}" var="l">
        <g:if test="${l.data[currentDay] != []}">
          <div class="report" style="background-color: #${l.color}">${l.data[currentDay]}</div>
        </g:if>
      </g:each>
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
    
    <br/>
    <table>
      <tr><th>Legend:</th></tr>
      <g:each in="${list}">
        <tr><th style="background-color: #${it.color}"></th><td>${it.projectName}</td></tr>
      </g:each>
    </table>
    <br/><br/><br/>
    <div class="signature"><g:message code="report.export.signature"/></div>
  </body>
</html>
