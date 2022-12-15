<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til FOG's carport quick byg menu.
    </jsp:attribute>



    <jsp:body>

        <br>


        <c:if test="${sessionScope.user == null}">
            <p>Log ind her, hvis du allerede er kunde hos os: <a
                    href="login.jsp">Log ind</a></p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>Hvis du ikke er kunde, kan du oprette dig her: <a href="createUser">Opret</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>