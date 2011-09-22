<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <title>${message(code:'confirm.title', default: 'Attention')}</title>
  </head>
  <body>
    <center>
    <h1>${message(code:'comfirm.message', default: 'Voulez-vous continuer?')}</h1>
    <g:link action="delete" id="${id}">${message(code:'confirm.yes', default: 'Oui')}</g:link>
    <g:link action="week">${message(code:'confirm.no', default: 'Non')}</g:link>
    </center>
  </body>
</html>