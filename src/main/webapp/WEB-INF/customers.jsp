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
                <th>Kunde</th>
                <th></th>
                <th>Se ordre</th>
            </tr>
            </thead>

            <c:forEach var="customer" items="${requestScope.userArrayList}">
                <c:if test="${customer.admin != true}">
                <tr>
                    <td>
                        <p>
                            <b>ID: </b> ${customer.userid}<br>
                            <b>Email: </b> ${customer.email}<br>
                            <b>Navn: </b> ${customer.name}<br>
                            <b>Dato: </b> ${customer.address}, ${customer.postalcode} ${customer.cityName}
                        </p>
                    </td>
                    <td>
                    </td>
                    <td>
                        <form>
                            <button formaction="viewCustomerPage" class="btn btn-primary" formmethod="post" name="userid"
                                    value="${customer.userid}">Se ordrer
                            </button>
                        </form>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>


    </jsp:body>

</t:pagetemplate>