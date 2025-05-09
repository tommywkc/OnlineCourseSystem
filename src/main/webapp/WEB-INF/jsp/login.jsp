<!DOCTYPE html>
<html lang="zh-HK">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" type="text/css">
    <title>Online Learning System</title>

</head>




<body>
<jsp:include page="header.jsp" />

<c:if test="${param.logout != null}">
    <p class="error-message" id="error-message">You have logged out.</p>
</c:if>

<div class="container-fluid">
    <!-- Course Name Section -->

    <div class="login-container">

        <h1 class="login-title"><spring:message code="button.login"/></h1>



        <form action="login" method="POST">
            <div class="input-group">
                <input type="text" id="ac" placeholder="<spring:message code="userName"/>" name="username" required>
            </div>
            <div class="input-group">
                <input type="password" id="pw" placeholder="<spring:message code="password"/>" name="password" required>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" id="login-button"  class="btn login-btn" value="login"><spring:message code="button.login"/></button>
        </form>

        <c:if test="${param.error != null}">
            <p class="error-message" id="error-message">Login failed.</p>
        </c:if>


        <a href="user/register" class="register-link"><spring:message code="register"/></a>

    </div>

</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>