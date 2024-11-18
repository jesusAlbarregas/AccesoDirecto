<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Aviso</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" />
    </head>
    <body>
        <div id="principal">
            <h2 class="error">${error}</h2>
            <p class="volver"><a href="${contexto}/FrontController">Volver</a></p>
        </div>
    </body>
</html>
