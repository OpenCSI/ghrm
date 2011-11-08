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
            <auth:isEmployee>
              <li><a href="${createLink(controller: 'report', action: 'create')}"><span class="menu_button alert-message success" >+</span></a></li>
              <li class="dropdown">
              <a class="dropdown-toggle" href="#">Reports</a>
              <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'report', action: 'month')}"><g:message code="global.month.current"/></a></li>
              <li><a href="${createLink(controller: 'report', action: 'week')}"><g:message code="global.week.current"/></a></li>
              </ul>
              </li>
            </auth:isEmployee>
            <auth:isProjectLeader>
              <li><a href="${createLink(controller:'project', action:'list')}"><g:message code="global.project"/></a></li>
            </auth:isProjectLeader>
            <auth:isHR>
              <g:if test="${grailsApplication.config.recruitment.status == 'start'}">
               <li class="dropdown">
                 <a class="dropdown-toggle" href="#"><g:message code="global.HR"/></a>
                 <ul class="dropdown-menu">
                  <li><a href="${createLink(controller:"HR", action:"recruitment")}"><g:message code="global.HR.recruitment"/></a></li>
                 </ul>
               </li>
              </g:if>
            </auth:isHR>
            <auth:isAdmin>
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
            </auth:isAdmin>
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
      <div class="well">
        <shiro:user>
        <h5><g:message code="global.project.actif"/></h5>
          <g:each in="${projectList}">
            <g:if test="${it.status == 0}">
              <g:if test="${it.customer.name != name}">
                <g:set var="name" value="${it.customer.name}"/>
                ${it.customer.name}
              </g:if>
              <li><a href="${createLink(controller:"project",action:"report",id:it.id)}">${it.name}</a></li>
            </g:if>
          </g:each>
        </shiro:user>
      </div>
    </div>

    <div id="extrainfo">
      <g:render template="/common/extraInfo" />
    </div>
    
    <div class="content">
      <g:if test="${flash.message}">
        <div class="alert-message warning">
        <div class="message">${flash.message}</div>
        </div>
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
