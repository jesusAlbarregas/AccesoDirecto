<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:url var="estilo" value="/CSS/estilo.css" scope="application" />
        <c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="INC/metas.inc"/>
        <title>Avistamientos</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" />
    </head>
    <body>
        <div id="principal">
        <h1>Página de avistamientos</h1>
        <form action="${contexto}/FrontController" method="post">
            <div class="dato">
                    <label>Introduce una anilla</label>
                    <input type="text" name="anilla" size="20" placeholder="Ej. 123" />
                    <div class="limpiar"></div>
                </div>
        
            <div class="dato">
                <input type="submit" value="Anilla" name="boton"/>
                <input type="submit" value="Todas" name="boton"/>
                <input type="submit" value="Algunas" name="boton"/>
            </div>
        
        </form>
        </div>
    </body>
</html>
