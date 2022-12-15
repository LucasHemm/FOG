<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen Admin.
    </jsp:attribute>
    
    <jsp:body>

        <form method="post">
            <button formaction="viewCustomers" class="btn btn-info">Kunder</button>
            <button formaction="viewAllOrders"class="btn btn-info">Ordrer</button>
            <button formaction="viewStorage"class="btn btn-info">Varelager</button>

        </form>
        
        
        
    </jsp:body>

</t:pagetemplate>