<%-- 
    Document   : unRegistro
    Created on : 26-oct-2017, 16:28:15
    Author     : Jesus
--%>

<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Aviso</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../INC/metas.inc"/>
        <title>Avistamientos</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" />
    </head>

    <body>
        <div id="principal">
            <h1>Datos de los avistamientos</h1>
            <table>
                <tr>
                <th>Anilla</th>
                <th>Especie</th>
                <th>Lugar</th>
                <th>Fecha</th>
            </tr>
            <c:forEach var="ave" items="${aves}">
            <tr>
                <td><c:out value="${ave.anilla}" /></td>
                <td><c:out value="${ave.especie}" /></td>
                <td><c:out value="${ave.lugar}" /></td>
                <td><c:out value="${ave.fecha}" /></td>
            </tr>
            </c:forEach>
                
            </table>
            <p class="volver"><a href="${contexto}/AccesoBD">Volver</a></p>
        </div>
    </body>
</html>
