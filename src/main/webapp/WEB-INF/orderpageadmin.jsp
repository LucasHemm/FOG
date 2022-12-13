<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Kunde ${requestScope.user.name}'s Stykliste og tegning.

    </jsp:attribute>


    <jsp:body>
        <h4>Mål på carport</h4>
        <h2><b>Længde: </b>${requestScope.partList.length} cm.</h2>
        <h2><b>Bredde: </b>${requestScope.partList.width} cm.</h2>
        <h2><b>Fulde pris: </b>${requestScope.partList.totalprice} kr.</h2>

        <p><b>Navn: </b> ${requestScope.user.name}
        <p><b>Email: </b> ${requestScope.user.email}
        <p><b>Adresse: </b> ${requestScope.user.address}, ${requestScope.user.postalcode} ${requestScope.user.cityName}</p>


        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Beskrivelse</th>
                <th>Længde cm.</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            </thead>
            <c:forEach var="part" items="${requestScope.partsArrayList}" varStatus="status">
                <c:if test="${part.part_Description != 'Placeholder/Deafault.'}">
                    <tr>
                        <td>
                                ${part.part_Description}
                        </td>
                        <td>
                            <c:if test="${part.partLength != 0}">
                                ${part.partLength}
                            </c:if>
                        </td>
                        <td>
                                ${requestScope.listOfAmounts.get(status.index)}
                        </td>
                        <td>
                                ${part.unit}
                        </td>
                        <td>
                                ${part.part_Usage}
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        ${requestScope.svg}


    </jsp:body>

</t:pagetemplate>