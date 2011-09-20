<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${resource(dir:'css',file:'bootstrap.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'opencsi.css')}" />
  <tooltip:resources/>
  <g:javascript library="application" />
<modalbox:modalIncludes />
<export:resource/>

  <title>OpenCSI: Ressources humaines</title>
</head>
<body>
  <div id="header">
    <img id="logoImage" src="${resource(dir:'images', file:'logo.png')}" alt="logo" />
  </div>

  <div id="firstPanel">
    <div id="menu">
      <ul>
        <li><langs:selector langs="fr, en, es"/></li>
        <li class="current">
          <a href="${createLink(url:'/')}">Home</a>
        </li>
        <li>
          <a href="#">Reports</a>
          <ul>
            <li><a href="${createLink(controller: 'report', action: 'week')}"><g:message code="global.week.current"/></a></li>
            <li><a href="${createLink(controller: 'report', action: 'create')}"><g:message code="global.report.new"/></a></li>
          </ul>
        </li>
        <auth:isProjectLeader>
          <li>
            <a href="#"><g:message code="global.project"/></a>
            <ul>
              <li><a href="${createLink(controller: 'project', action: 'create')}"><g:message code="global.new" /></a></li>
              <li><a href="${createLink(controller: 'project', action: 'list')}"><g:message code="global.list" /></a></li>
            </ul>
          </li>
          <li>
            <a href="#"><g:message code="global.customer"/></a>
            <ul>
              <li><a href="${createLink(action:"create", controller:"customer")}"><g:message code="global.new"/></a></li>
              <li><a href="${createLink(action:"list", controller:"customer")}"><g:message code="global.list"/></a></li>
            </ul>
          </li>
        </auth:isProjectLeader>
        <auth:isAdmin>
          <li>
            <a href="#">Admin</a>
            <ul>
              <li><a href="${createLink(controller:"task", action:"create")}"><g:message code="global.task.create"/></a></li>
              <li><a href="${createLink(controller:"task", action:"list")}"><g:message code="global.task.list"/></a></li>
              <li><a href="${createLink(controller:"user", action:"create")}"><g:message code="global.user.create"/></a></li>
              <li><a href="${createLink(controller:'user',action:'list')}"><g:message code="global.user.list"/></a></li>
            </ul>
          </li>
        </auth:isAdmin>
        <li><a href="${createLink(action:'signOut', controller:'auth')}"><g:message code="global.logout"/></a></li>
      </ul>
    </div>
    <div id="extrainfo">
      <g:render template="/common/extraInfo" />
    </div>
  </div>
  <div id="secondPanel">
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <g:layoutBody />
  </div>
</body>
</html>
