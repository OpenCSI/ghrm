<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
      <meta name="layout" content="opencsi" />
    </head>
  <body>
    <table>
      <tbody>
      <th><g:message code="recruitment.more.title"/></th>
      <tr>
        <td>
        <div class="left">id:${fieldValue(bean: recruitment, field: "id")}</div>
        <div class="right">STATUT:<b>${fieldValue(bean: recruitment, field: "statut.name")}</b></div><br>
        <u><g:message code="recruitment.more.message.title"/></u><div class="center">${fieldValue(bean: recruitment, field: "title")}</div><br>
        <u><g:message code="recruitment.more.message.content"/></u><br><div class="center">${fieldValue(bean: recruitment, field: "comment")}</div><br>
        <g:message code="recruitment.more.message.from"/> ${fieldValue(bean: recruitment, field: "who")}<br>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="export">
      <g:if test="${recruitment.file == ''}">
        <b><g:message code="recruitment.more.message.file"/></b>
      </g:if>
      <g:if test="${recruitment.file != 'No file'}">
        <a href="/ghrm/recruitment/${recruitment.who}/${recruitment.file}"><b>${recruitment.file}</b></a>
      </g:if>
    </div>
    <br>
    <g:form action="modify">
      <input type="hidden" name="id" value="${recruitment.id}"/>
      <table>
        <th><g:message code="recruitment.more.statut.change.title"/></th>
        <tr><td>
          <select name="statut">
            <g:each in="${status}" var="stat">
              <option>${stat.name}</option>
            </g:each>
          </select>
          <input type="submit" value="<g:message code="recruitment.more.modify" />"/>
        </td></tr>
      </table>
    </g:form>
    <g:form action="modify">
      <input type="hidden" name="id" value="${recruitment.id}"/>
    <table>
      <th><g:message code="recruitment.more.answer.title"/></th>
      <tr>
        <td>
          <g:message code="recruitment.more.message.title"/><div class="center"><input type="text" name="title" value="Re:'${recruitment.title}'"/></div><br>
          <g:message code="recruitment.more.message.content"/><div class="center"><textarea name="content" rows="5" cols="35"></textarea></div><br>
          <div class="center"><input type="submit" value="<g:message code="recruitment.more.answer.send"/>"/></div>
        </td>
      </tr>
    </table>
    </g:form>
  <br>

  <table>
    <th><g:message code="recruitment.more.dialog"/></th>
    <g:each in="${message}" var="message">
      <tr><td>
      <g:if test="${recruitment.user.name == message.who}">
        <b>
      </g:if>
      ${message.who} <g:message code="recruitment.more.dialog.wrote"/> :
          <div class="right"><g:message code="recruitment.more.dialog.date"/> ${fieldValue(bean: message, field: "createat")}</div>
          <g:message code="recruitment.more.message.title"/> <div class="center">${fieldValue(bean: message, field: "title")}</div><br>
          <g:message code="recruitment.more.message.content"/> <div class="center">${fieldValue(bean: message, field: "message")}</div><br>
      <g:if test="${recruitment.user.name == message.who}">
        </b>
      </g:if>
      <hr>
      </td></tr>
    </g:each>
  </table>
  <br>
  </body>
</html>
