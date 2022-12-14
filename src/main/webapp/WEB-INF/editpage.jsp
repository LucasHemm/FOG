<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til FOG's quick-byg menu
    </jsp:attribute>


    <jsp:body>
        <h1>redigerings side</h1>
        <h2>${requestScope.msg}</h2>

        <form action="editPart" method="post">
            <input type="hidden" id="partid" name="partid" value="${requestScope.partid}">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Nuværende</th>
                    <th>Ny</th>
                </tr>
                </thead>
                <tr>
                    <td>
                            ${requestScope.part.part_Description}
                    </td>

                    <td>
                        <label for="description">Beskrivelse: </label><br>
                        <input type="text" id="description" name="description"
                               value="${requestScope.part.part_Description}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                            ${requestScope.part.pricePrUnit}
                    </td>
                    <td>
                        <label for="price">Pris: </label><br>
                        <input type="number" min="0" id="price" name="price" value="${requestScope.part.pricePrUnit}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                            ${requestScope.part.costPricePrUnit}
                    </td>
                    <td>
                        <label for="costprice">Indkøbspris: </label><br>
                        <input type="number" min="0" id="costprice" name="costprice"
                               value="${requestScope.part.costPricePrUnit}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                            ${requestScope.part.part_Usage}
                    </td>
                    <td>
                        <label for="usage">Beskrivelse: </label><br>
                        <input type="text" id="usage" name="usage" value="${requestScope.part.part_Usage}"/>
                    </td>
                </tr>
            </table>
            <button type="submit" class="btn btn-info" class="float-end">Rediger</button>
        </form>
    </jsp:body>

</t:pagetemplate>