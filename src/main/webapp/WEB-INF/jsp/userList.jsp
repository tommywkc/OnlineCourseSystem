<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/userEdit.css" rel="stylesheet" type="text/css">    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" type="text/css">
    <title>Online Learning System</title>
</head>

<body>
<jsp:include page="header.jsp" />
<div class="container-fluid">
    <!-- Course Name Section -->

    <div class="login-container">

        <h1 class="login-title"> <spring:message code="userlist"/></h1>

        <c:choose>
            <c:when test="${fn:length(userlist) == 0}">
                <i><spring:message code="thereAreNoUsersInTheSystem"/></i>

            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th><spring:message code="userName"/></th><th><spring:message code="role"/></th><th><spring:message code="phoneNumber"/></th><th><spring:message code="email"/></th><th><spring:message code="fullName"/></th><th></th><th></th>
                    </tr>
                    <c:forEach items="${userlist}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.userRole}</td>
                            <td>${user.phone}</td>
                            <td>${user.email}</td>
                            <td>${user.fullName}</td>
                            <td>
<%--                                [<a href="<c:url value="/user/edit/${user.username}" />">update</a>]--%>
                                <form action="${pageContext.request.contextPath}/user/edit/${user.username}" method="GET">
                                    <input type="submit" class="btn btn-warning" value="<spring:message code="button.edit"/>" aria-label=<spring:message code='poll'/>/>
                                </form>
                            </td>
                            <td>
<%--                                [<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>]--%>
                                <form action="${pageContext.request.contextPath}/user/delete/${user.username}" method="GET">
                                    <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label=<spring:message code='poll'/>/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
<%--        [<a href="<c:url value="/user/register" />">add</a>]--%>

        <form action="${pageContext.request.contextPath}/user/register" method="GET" style="display: inline;">
            <button type="submit" class="btn btn-warning">
                <spring:message code="addUser"/>
            </button>
        </form>
    </div>

</div>
</body>
</html>