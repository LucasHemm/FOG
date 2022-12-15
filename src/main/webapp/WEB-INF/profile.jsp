<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til din profil hos FOG.
    </jsp:attribute>


    <jsp:body>

        <h5>${requestScope.msg}</h5>

        <p><b>Navn:</b> ${sessionScope.user.name} <br/>
            <b>Email:</b> ${sessionScope.user.email}<br/>
            <b>Adresse:</b> ${sessionScope.user.address}, ${sessionScope.user.postalcode} ${sessionScope.user.cityName}
        </p>


        <table class="table table-striped">
            <thead>
            <tr>
                <th>Ordrer</th>
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