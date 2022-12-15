<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Log ind her.
    </jsp:attribute>



    <jsp:body>

        <h3>Log ind her</h3>

        <form action="login" method="post">
            <label for="email">E-mail: </label><br>
            <input type="text" id="email" name="email"/>
            <br>
            <label for="password">Kodeord: </label><br>
            <input type="password" id="password" name="password"/><br><br>
            <button type="submit" class="btn btn-primary">Log ind</button>
        </form>

    </jsp:body>
</t:pagetemplate>