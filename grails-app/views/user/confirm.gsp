<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <title>${message(code:'confirm.title', default: 'Attention')}</title>
  </head>
  <body>
    <script type="text/javascript">
      document.getElementById('id_no').focus();
     </script>
    <center>
    <h1>${message(code:'confirm.message', default: 'Voulez-vous continuer?')}</h1>
    <g:link action="list" >${message(code:'confirm.no', default: 'Non')}</g:link> 
    <g:link action="delete" id="${id}">${message(code:'confirm.yes', default: 'Oui')}</g:link>
    </center>
  </body>
</html>
