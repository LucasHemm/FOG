<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til din profil hos FOG.
    </jsp:attribute>




    <jsp:body>

        ${requestScope.msg}
        ${requestScope.orderList} - her er listen

        <p>SIUUUU</p>
    </jsp:body>

</t:pagetemplate>