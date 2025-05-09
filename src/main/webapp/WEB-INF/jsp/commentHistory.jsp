<!DOCTYPE html>
<html lang="zh-HK">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/userEdit.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
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
            <div class="col-md-6 col-12">
                <div class="lecture-container">
                    <h4 class="login-title"><spring:message code="lecture"/><spring:message code="comment"/></h4>
                    <ul>
                        <c:forEach items="${lectures}" var="lccomment">
                            <li>
<%--                                    [${lccomment.get(2)}] - [${lccomment.get(0)}] ${username} : <br>${lccomment.get(1)}--%>
        [${lccomment.get(2)}]&nbsp;${username}&nbsp;<spring:message code="in"/>&nbsp;[${lccomment.get(0)}]&nbsp;<spring:message code="commented"/>:<br>"${lccomment.get(1)}"
                            </li>

                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-12">
                <div class="poll-container">
                    <h4 class="login-title"><spring:message code="poll"/><spring:message code="comment"/></h4>
                    <ul>
                        <c:forEach items="${polls}" var="plcomment">
                            <li>
<%--                                [${plcomment.get(2)}] - [${plcomment.get(0)}] ${username} : <br>${plcomment.get(1)}--%>
                                [${plcomment.get(2)}]&nbsp;${username}&nbsp;<spring:message code="in"/>&nbsp;[${plcomment.get(0)}]&nbsp;<spring:message code="commented"/>:<br>"${plcomment.get(1)}"
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