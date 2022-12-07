<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        <Title> Fog: Opret Profil </Title>

    </jsp:attribute>

    <jsp:body>

         <h3>Her kan du oprette dig</h3>
        <form action="createUser" method="post">
            <label for="name">Navn: </label><br>
            <input type="text" id="name" name="name"/><br><br>

            <label for="email">Email: </label><br>
            <input type="text" id="email" name="email"/>
            <p>${requestScope.msg1}</p>

            <label for="password1">Password: </label><br>
            <input type="password" id="password1" name="password1"/><br><br>
            <label for="password2">Gentag Password: </label><br>
            <input type="password" id="password2" name="password2"/>
            <p>${requestScope.msg2}</p>

            <label for="city">By: </label><br>
            <input type="text" id="city" name="city" /><br><br>
            <label for="address">Adresse: </label><br>
            <input type="text" id="address" name="address" /><br><br>
            <label for="postalcode">Postnummer: </label><br>
            <input type="text" id="postalcode" name="postalcode" min="1000" max="9999"/><br><br>


            <input type="submit" class="btn btn-info" value="Opret"/>
        </form>

    </jsp:body>

</t:pagetemplate>