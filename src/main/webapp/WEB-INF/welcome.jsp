<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til FOG's quick-byg menu
    </jsp:attribute>



    <jsp:body>

        <br/>
        <h3>Venligst skriv længde og bredde på din ønskede carport.</h3>

        <form action="createOrder" method="post">

            <label for="width">Vælg en bredde:</label>
            <select name="width" id="width">
                <c:forEach var="i" begin="240" end="600" step="30" >
                    <option value="${i}">${i} cm</option>
                </c:forEach>
            </select>


            <label for="length">Vælg en længde:</label>
            <select name="length" id="length">
                <c:forEach var="i" begin="240" end="780" step="30" >
                    <option value="${i}">${i} cm</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Lav forespørgsel</button>

        </form>

    </jsp:body>

</t:pagetemplate>