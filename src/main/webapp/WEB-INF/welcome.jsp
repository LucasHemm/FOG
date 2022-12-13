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
                <c:forEach var="i" begin="240" end="600" step="30">
                    <option value="${i}">${i} cm</option>
                </c:forEach>
            </select>


            <label for="length">Vælg en længde:</label>
            <select name="length" id="length">
                <c:forEach var="i" begin="240" end="780" step="30">
                    <option value="${i}">${i} cm</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Lav forespørgsel</button>


            <p>Tilvalg af skur</p>
            <label for="withoutshed">Uden skur</label>
            <input type="radio" id="withoutshed" name="hasShed" checked="checked" value="0">
            <label for="withshed">Med skur</label>
            <input type="radio" id="withshed" name="hasShed" value="1">
            <br>

            <p>Bredde på skur</p>
            <label for="full">Fuld bredde</label>
            <input type="radio" id="full" name="shedwidth" checked="checked" value="1">
            <label for="half">Halv bredde</label>
            <input type="radio" id="half" name="shedwidth" value="2">

            <br>
            <label for="shedlength">Vælg en længde:</label>
            <select name="shedlength" id="shedlength">
                    <option value="4">Lille - 25% af længden.</option>
                    <option selected="selected" value="3">Mellem - 33% af længden.</option>
                    <option value="$2">Stor - 50% af længden.</option>
            </select>


        </form>

    </jsp:body>

</t:pagetemplate>