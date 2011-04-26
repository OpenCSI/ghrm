<!DOCTYPE html>
<html>
  <head>
    <title><g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  <g:layoutHead />
  <g:javascript library="application" />
</head>
<body>
  
  <div id="spinner" class="spinner" style="display:none;">
    <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
  </div>
  <div id="grailsLogo"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></div>
  <div class="primary">
    <span class="menuButton"><a class="home" href="/ghrm/">Home</a></span>
    <span class="menuButton"><a href="/ghrm/customer/list" class="list">Customer</a></span>
    <span class="menuButton"><a href="/ghrm/project/list" class="list">Project</a></span>
    <span class="menuButton"><a href="/ghrm/user/list" class="list">User</a></span>
    <span class="menuButton"><a href="/ghrm/report/list" class="list">Report</a></span>
  </div>
  <div class="body">
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>

    <g:layoutBody />
  </div>
</body>
</html>