<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Varelager.
    </jsp:attribute>


    <jsp:body>


        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Beskrivelse</th>
                <th>Pris pr. enhed</th>
                <th>Indkøbspris pr. enhed</th>
                <th>Enhed</th>
                <th>Beskrivelse</th>
                <th>Længder</th>
                <th>Rediger</th>
            </tr>
            </thead>
            <c:forEach var="map" items="${requestScope.partsMap}">
                <c:if test="${map.value.part_Description != 'Placeholder/Deafault.'}">
                    <tr>
                        <td>
                                ${map.value.part_Description}
                        </td>

                        <td>
                                ${map.value.pricePrUnit} kr.
                        </td>

                        <td>
                                ${map.value.costPricePrUnit} kr.
                        </td>

                        <td>
                                ${map.value.unit}
                        </td>

                        <td>
                                ${map.value.part_Usage}
                        </td>

                        <td>
                            <c:if test="${map.value.priceUnit == 'M'}">

                                <label for="length">Længder:</label>

                                <select name="length" id="length">
                                    <c:forEach var="map2" items="${requestScope.lengthMap}">
                                        <c:if test="${map.key == map2.key}">
                                            <c:forEach var="value" items="${map2.value}">
                                                <option value="${value}">${value} cm
                                                </option>
                                            </c:forEach>
                                        </c:if>

                                    </c:forEach>
                                </select>
                            </c:if>


                        </td>
                        <td>
                            <form action="vieweditPage" method="post">
                                <input type="hidden" id="partid" name="partid" value="${map.key}">
                                <button type="submit" class="btn btn-info">Rediger</button>
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</t:pagetemplate>