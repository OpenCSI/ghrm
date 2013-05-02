
<%@ page import="com.opencsi.ghrm.services.*" %>
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
    <title>PDF Week</title>
  </head>
  <body>
    <center><h1>${client}</h1></center>
    <center>${date}</center>
     <g:if test="${picture}">
      <span class="picture"><img src="data:${typePicture};base64,${picture}" alt="${name}"/></span>
    </g:if>
    
    <table class="reportTable">
      <thead>
        <tr>
          <th style="width: 10%">Project</th>
       <g:set var="nameOfDays" value="${new CalendarService().getNameOfDays()}" />
       <g:each in="${1..7}" var="i">
        <th>
          <g:message code="day.${i}"/> <br/> ${weekInfos[i]['day']} <g:message code="month.${weekInfos[i]['month']}"/>
        </th>
       </g:each>
       </tr>
      </thead>
      <tbody>
        <g:each in="${list}">
          <tr><td>${it.projectName}</td>
          <g:each in="${1..7}" var="i">
            <g:if test="${it.data[i] == []}">
              <td></td>
            </g:if>
            <g:else>
              <td>${it.data[i]}</td>
            </g:else>
          </g:each>
          </tr>
        </g:each>
      </tbody>
    </table>
    <br/><br/><br/>
    <div class="signature"><g:message code="report.export.signature"/></div>
    
  </body>
</html>
