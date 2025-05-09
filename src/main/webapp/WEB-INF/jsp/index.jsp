<!DOCTYPE html>
<html lang="zh-HK">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" type="text/css">
    <title>Online Learning System</title>

</head>
<body>

<jsp:include page="header.jsp" />

<div class="container-fluid">


    <!-- Course Name Section -->
    <div class="row mb-4">
        <div class="col text-center">
            <h2 class="course-title "> <spring:message code="course"/>：COMPS S380F</h2>
        </div>
    </div>

    <!-- Lecture and Poll Section -->
    <div class="row">
        <div class="col-md-6 col-12">
            <div class="lecture-container">
                <h2 class="lecture-title"><spring:message code="lecture"/></h2>
                <ul class="lecture-links list-unstyled">
                    <c:forEach items="${lectureDatabase}" var="lecture">
                        <li class="mb-3">
                            <div style="display: flex; justify-content: center;">
<%--                                    ${lecture.lectureName}:--%>
<%--                                    <a href=" <c:url value="/lecture/${lecture.lectureId}"/>">--%>
<%--                                    <c:out value="${lecture.lectureId}"></c:out>></a>--%>


                                    <form action="${pageContext.request.contextPath}/lecture/${lecture.lectureId}" method="GET" style="margin-right: 10px;">
                                        <input type="submit" class="list-btn" value="<spring:message code='lecture'/> :${lecture.lectureName}" aria-label="<spring:message code='lecture'/>: ${lecture.lectureName}"/>
                                    </form>

                                    <security:authorize access="hasRole('TEACHER')">
<%--                                        Delete Lecture--%>
<%--                                        <a href=" <c:url value="/lecture/delete/${lecture.lectureId}"/>">--%>
<%--                                            <c:out value="${lecture.lectureId}"></c:out></a>--%>

                                        <form action="${pageContext.request.contextPath}/lecture/delete/${lecture.lectureId}" method="GET">
                                            <%--<input type="hidden" name="lectureId" value="${lecture}"/>--%>
                                            <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label="Delete lecture: ${lecture}"/>
                                        </form>
                                    </security:authorize>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <security:authorize access="hasRole('TEACHER')">
                    <form action="${pageContext.request.contextPath}/lecture/create" method="GET">
                        <div class="input-group mb-3">
                            <input type="text" name="lectureName" class="form-control" placeholder="<spring:message code="newLectureName"/>" required/>
                            <div class="input-group-append">
                                <button class="btn login-btn" type="submit"><spring:message code="button.add"/></button>
                            </div>
                        </div>
                    </form>
                </security:authorize>
            </div>
        </div>

        <div class="col-md-6 col-12">
            <div class="poll-container">
                <div class="row justify-content-center">
                    <h2 class="poll-title"><spring:message code="poll"/></h2>
                </div>
                <ul class="poll-links list-unstyled">
                    <c:choose>
                        <c:when test="${fn:length(pollDatabase) == 0}">
                            <i><spring:message code='thereAreNoTicktsInTheSystem'/></i>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${pollDatabase}" var="poll">
                                <li class="mb-3">
                                    <div style="display: flex; justify-content: center;">
                                            <%--                                    ${poll.pollName}:--%>
                                            <%--                                <a href=" <c:url value="/poll/${poll.pollId}"/>">--%>
                                            <%--                                    <c:out value="${poll.pollId}"></c:out></a>--%>

                                        <form action="${pageContext.request.contextPath}/poll/view/${poll.pollId}" method="GET" style="margin-right: 10px;">
                                            <input type="submit" class="list-btn" value="<spring:message code='question'/> :${poll.question}" aria-label="<spring:message code='question'/> :${poll.question}}"/>
                                        </form>
                                        <security:authorize access="hasRole('TEACHER')">
                                            <%--                                    Delete pool--%>
                                            <%--                                    <a href=" <c:url value="/poll/delete/${poll.pollId}"/>">--%>
                                            <%--                                        <c:out value="${poll.pollId}"></c:out></a>--%>


                                            <form action="${pageContext.request.contextPath}/poll/delete/${poll.pollId}" method="GET">
                                                <input type="hidden" name="pollId" value="${poll.pollId}}"/>
                                                <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label=<spring:message code='poll'/>/>
                                            </form>

                                        </security:authorize>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <security:authorize access="hasRole('TEACHER')">
                    <%--                    Add New poll--%>
                    <%--                    <a href=" <c:url value="/poll/add"/>"></a>--%>

                    <form action="${pageContext.request.contextPath}/poll/create" method="GET">
                        <input type="submit" class="btn login-btn" value="<spring:message code="button.addPoll"/>" aria-label="Add Poll">
                    </form>

                </security:authorize>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>