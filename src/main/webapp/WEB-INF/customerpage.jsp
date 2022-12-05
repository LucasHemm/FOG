<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Kunde ${requestScope.user.name}'s profil.
    </jsp:attribute>

    <jsp:body>


        <p><b>Name:</b> ${requestScope.user.name} <br/>
            <b>Email:</b> ${requestScope.user.email}<br/>
            <b>Adresse:</b> ${requestScope.user.address}, ${sessionScope.user.postalcode} ${sessionScope.user.cityName}
        </p>


        <table class="table table-striped">
            <thead>
            <tr>
                <th>Ordre</th>
                <th>Status</th>
                <th>Se tegning og stykliste</th>
            </tr>
            </thead>

            <c:forEach var="order" items="${requestScope.orderList}">
                <tr>
                    <td>
                        <p>

                            <b>Ordre id:</b> ${order.orderid}
                            <b>Dato:</b> ${order.timestamp}

                        </p>

                    </td>

                    <td>
                            ${order.status}
                    </td>

                    <td>
                        <form>
                            <button formaction="viewProfileOrder" class="btn btn-primary" formmethod="post" name="number"
                                    value="${requestScope.orderList.indexOf(order)}">Se information
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </jsp:body>

</t:pagetemplate>