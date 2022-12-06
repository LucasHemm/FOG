<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen Admin.
    </jsp:attribute>
    
    <jsp:body>


        <table class="table table-striped">
            <thead>
            <tr>
                <th>Ordre</th>
                <th>Status</th>
                <th>Se tegning og stykliste</th>
            </tr>
            </thead>

            <c:forEach var="order" items="${sessionScope.allOrdersList}">
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
                            <button formaction="viewOrderFromAllOrders" class="btn btn-primary" formmethod="post" name="orderindex"
                                    value="${sessionScope.allOrdersList.indexOf(order)}">Se information
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </jsp:body>

</t:pagetemplate>