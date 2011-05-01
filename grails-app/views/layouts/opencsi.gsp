<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${resource(dir:'css',file:'opencsi.css')}" />
    <tooltip:resources/>
    <title>OpenCSI: Ressources humaines</title>
  </head>
  <body>
    <div id="header">
      <img id="logoImage" src="${resource(dir:'images', file:'logo.png')}" alt="logo" />
    </div>

    <div id="firstPanel">
      <div id="menu">
        <ul>
          <li class="current">
            <a href="${createLink(url:'/')}">Home</a>
          </li>
          <li>
            <a href="#">Reports</a>
            <ul>
              <li><a href="${createLink(controller: 'report', action: 'week')}">Current week</a></li>
              <li><a href="${createLink(controller: 'report', action: 'create')}">Create report</a></li>
            </ul>
          </li>
          <li>
            <a href="#">Projects</a>
            <ul>
              <li><a href="${createLink(controller: 'project', action: 'report', id: '1')}">Report</a></li>
              <li><a href="${createLink(controller: 'project', action: 'create')}">Report</a></li>
            </ul>
          </li>
          <li>
            <a href="#">Customers</a>
            <ul>
              <li><a href="${createLink(action:"create", controller:"customer")}">New</a></li>
              <li><a href="#">Search</a></li>
            </ul>
          </li>
          <li>Admin
            <ul>
              <li><a href="${createLink(controller:"task", action:"create")}">Create task</a></li>
              <li><a href="${createLink(controller:"user", action:"create")}">Create user</a></li>
            </ul>
          </li>
          <li><a href="${createLink(action:'signOut', controller:'auth')}">Logout</a></li>
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
