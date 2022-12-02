<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>


    <jsp:body>

        <h1>STYKLISTE</h1>
        <h4>Mål på carport</h4>
        <h2><b>Længde: </b>${requestScope.partList.length} cm.</h2>
        <h2><b>Bredde: </b>${requestScope.partList.width} cm.</h2>

        <h5></h5>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Beskrivelse</th>
                <th>Længde cm.</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
            </tr>
            </thead>

            <c:forEach var="part" items="${requestScope.partsArrayList}">
                <tr>
                    <td>
                            ${part.part_Description}
                    </td>
                    <td>
                        <c:if test = "${part.partLength != 0}">
                            ${part.partLength}
                        </c:if>
                    </td>
                    <td>
                        antal af stk
                    </td>
                    <td>
                        ${part.unit}
                    </td>
                    <td>
                            ${part.part_Usage}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>

</t:pagetemplate>