<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${resource(dir:'css',file:'opencsi.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'superfish.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'superfish-vertical.css')}" />
    <script type="text/javascript" src="${resource(dir:'js',file:'jquery/jquery-1.5.2.js')}"></script>
    <script type="text/javascript" src="${resource(dir:'js',file:'jquery/jquery.hoverIntent.js')}"></script>
    <script type="text/javascript" src="${resource(dir:'js',file:'superfish.js')}"></script>
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
          <a href="#a">Home</a>
        </li>
        <li>
          <a href="#">Projects</a>
          <ul>
            <li><a href="#">New</a></li>
            <li><a href="#">Search</a></li>
          </ul>
        </li>
        <li>
          <a href="#">Customers</a>
          <ul>
            <li><a href="#">New</a></li>
            <li><a href="#">Search</a></li>
          </ul>
        </li>
      </ul>
      </div>
    </div>
    <div id="secondPanel">
      <g:layoutBody />
    </div>
  </body>
</html>
