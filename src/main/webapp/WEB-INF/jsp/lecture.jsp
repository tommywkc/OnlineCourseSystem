<!DOCTYPE html>
<html lang="zh-HK">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" type="text/css">
    <title>Online Learning System</title>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container-fluid">
    <!-- Course Name Section -->
    <div class="row mb-4">
        <div class="col text-center">
            <h2 class="course-title"><spring:message code="lecture"/>${lectureName}</h2>
        </div>
    </div>


    <div class="row">
        <div class="col-md-6 col-12">
            <div class="lecture-container">
                <h2 class="lecture-title"><spring:message code="downloadlink"/></h2>
                <ul class="lecture-links list-unstyled">
                    <c:choose>
                            <c:when test="${fn:length(notelist) == 0}">
                                <i><spring:message code="thereIsNoNoteInTheSystem"/></i>
                            </c:when>
                        <c:otherwise>
                            <c:forEach items="${notelist}" var="note">
                                <li class="mb-3">
                                    <div style="display: flex; justify-content: space-between; align-items: center;">
                                        <form action="${pageContext.request.contextPath}/lecture/${lectureId}/note/${note.noteId}" method="GET" style="margin-right: 10px;">
                                            <input type="submit" class="list-btn" value="${note.noteName}" aria-label="${note.noteName}"/>
                                        </form>
                                        <security:authorize access="hasRole('TEACHER')">
                                            <form action="${pageContext.request.contextPath}/lecture/${lectureId}/note/delete/${note.noteId}" method="GET">
                                                    <%--<input type="hidden" name="lectureId" value="${lecture}"/>--%>
                                                <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label="Delete Note: ${note}"/>
                                            </form>
                                        </security:authorize>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <security:authorize access="hasRole('TEACHER')">
                    <form action="${pageContext.request.contextPath}/lecture/createNote/${lectureId}" method="GET">
                        <div class="input-group-wrapper">
                            <div class="input-group mb-3">
                                <input type="submit" class="btn login-btn" value="<spring:message code='button.addNote'/>" aria-label="Add Note"/>
                            </div>
                        </div>
                    </form>
                </security:authorize>
            </div>
        </div>



        <div class="col-md-6 col-12">
            <div class="poll-container">
                <h2 class="poll-title">Comments</h2>
                <ul class="lecture-links list-unstyled">
                    <c:choose>
                            <c:when test="${fn:length(commentlist) == 0}">
                                <i><spring:message code="thereIsNoCommentInTheSystem"/></i>
                            </c:when>
                        <c:otherwise>
                            <c:forEach items="${commentlist}" var="comment">
                                <li class="mb-3 comment-container">
                                    <div style="display: flex; justify-content: space-between; align-items: center;">
                                        <form>
                                            <i>${comment.username} : ${comment.comment}</i>
                                        </form>
                                        <security:authorize access="hasRole('TEACHER')">
                                            <form action="${pageContext.request.contextPath}/lecture/${lectureId}/deleteComment/${comment.lcId}" method="GET">
                                                <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label="Delete Comment: ${comment}"/>
                                            </form>
                                        </security:authorize>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <form action="${pageContext.request.contextPath}/lecture/${lectureId}/addComment" method="GET">
                <div class="input-group mb-3">

                        <input type="text" name="comment" class="form-control" placeholder="<spring:message code="enterYourComment"/>" required aria-label="Comment"/>
                        <div class="input-group-append">
                            <button class="btn login-btn" type="submit"><spring:message code="button.submit"/></button>
                        </div>

                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>