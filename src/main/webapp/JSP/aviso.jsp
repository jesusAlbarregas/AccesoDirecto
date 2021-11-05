<%-- 
    Document   : error
    Created on : 26-oct-2017, 16:24:26
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aviso</title>
    </head>
    <body>
        <h2><%=request.getAttribute("aviso") %></h2>
        <p><a href="<%=request.getContextPath()%>/AccesoBD">Volver</a></p>
    </body>
</html>
