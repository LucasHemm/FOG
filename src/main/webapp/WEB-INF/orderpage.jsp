<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>


    <jsp:body>

        <h1>DETTE ER STYKLISTE SIDEN</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Beskrivelse</th>
                <th>Længde</th>
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
                            ${part.partLength}
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