<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet/less" type="text/css" href="${resource(dir:'bootstrap/lib',file:'bootstrap.less')}">
    <link rel="stylesheet/less" type="text/css" href="${resource(dir:'css',file:'opencsi.css')}">
    <script type="text/javascript" src="${resource(dir:'bootstrap/js',file:'less-1.1.3.min.js')}" ></script>
    <script type="text/javascript" src="${resource(dir:'bootstrap/js',file:'jquery.min.js')}" ></script>
    <script type="text/javascript" src="${resource(dir:'bootstrap/js',file:'bootstrap-dropdown.js')}" ></script>

  <tooltip:resources/>
  <g:javascript library="application" />
  <modalbox:modalIncludes />
  <export:resource/>

  <title>OpenCSI: Ressources humaines</title>
</head>
<body style="padding-top: 50px;">

  <div class="topbar-wrapper" style="z-index: 5;">
    <div class="topbar" data-dropdown="dropdown">
      <div class="topbar-inner">
        <div class="container">
          <h3><a href="#">Human Resources</a></h3>
          <ul class="nav">
            <li><a href="${createLink(controller: 'report', action: 'create')}"><span class="menu_button alert-message success" >+</span></a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Projets</a></li>
            <li class="dropdown">
              <a class="dropdown-toggle" href="#">Admin</a>
              <ul class="dropdown-menu">
                <li><a href="${createLink(controller:"task", action:"list")}"><g:message code="task.manage"/></a></li>
                <li><a href="${createLink(controller:"customer", action:"list")}"><g:message code="customer.manage"/></a></li>
                <li></li>
                <li class="divider"></li>
                <li><a href="${createLink(controller:"user", action:"list")}"><g:message code="user.manage"/></a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle">Dropdown</a>
              <ul class="dropdown-menu">
                <li><a href="#">Secondary link</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li><a href="#">Another link</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav secondary-nav">
            <li><a href="${createLink(action:'signOut', controller:'auth')}"><g:message code="global.logout"/></a></li>

          </ul>
        </div>
      </div><!-- /topbar-inner -->
    </div><!-- /topbar -->
  </div>


  <div class="container-fluid">
    <div class="sidebar">
      <ul>
        <li class="current">
          <a href="${createLink(url:'/')}">Home</a>
        </li>
        <auth:isEmployee>
          <li>
            <a href="#">Reports</a>
            <ul>
              <li><a href="${createLink(controller: 'report', action: 'month')}"><g:message code="global.month.current"/></a></li>
              <li><a href="${createLink(controller: 'report', action: 'week')}"><g:message code="global.week.current"/></a></li>
              <li><a href="${createLink(controller: 'report', action: 'create')}"><g:message code="global.report.new"/></a></li>
            </ul>
          </li>
        </auth:isEmployee>
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
        <auth:isHR>
          <li>
            <a href="#"><g:message code="global.HR"/></a>
            <ul><li><a href="${createLink(controller:"HR", action:"recruitment")}"><g:message code="global.HR.recruitment"/></a></li></ul>
          </li>
        </auth:isHR>
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
    <div class="content">
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:layoutBody />
    </div>
  </div>
  <div id="footer">
    <hr size="1px" />
    <langs:selector langs="fr, en, es"/>
    Powered by OpenCSI
  </div>
</body>
</html>
