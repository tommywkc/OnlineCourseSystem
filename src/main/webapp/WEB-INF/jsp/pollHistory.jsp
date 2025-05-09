<!DOCTYPE html>
<html lang="zh-HK">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/userEdit.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" type="text/css">
    <title>Online Learning System</title>
</head>
<style>
    .header-container {
        display: flex;
        align-items: center;
        position: relative;
        margin-bottom: 20px;
    }
    .history-title {
        margin: 0;
        text-align: center;
        flex-grow: 1;
    }
</style>
<body>
<jsp:include page="header.jsp" />

<div class="container-fluid">

    <div class="row">
        <div class="col-md-12 col-12">
            <div class="login-container">
                <h4 class="login-title"><spring:message code="poll"/><spring:message code="button.history"/></h4>
                <ul>
                    <c:forEach items="${polls}" var="poll">
                        <li>
<%--                            <i>In poll [${poll.get(0)}] ${username} voted:<br>${poll.get(1)}</i>--%>
                            <i>${username}&nbsp;<spring:message code="in"/>&nbsp;[${poll.get(0)}]&nbsp;<spring:message code="voted"/>:<br>"${poll.get(1)}"</i>

                        </li>

                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>