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
            <h3 class="course-title">${note.noteName}</h3>

        </div>
    </div>


    <div class="row">
        <div class="col-md-6 col-12">
            <div class="lecture-container">
                <h2 class="lecture-title"><spring:message code="downloadlink"/></h2>
                <ul class="lecture-links list-unstyled">
                    <c:choose>
                        <c:when test="${fn:length(note.attachments) == 0}">
                            <i>There is no attachment in the system.</i>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${note.attachments}" var="attachment" varStatus="status">
                                <li class="mb-3">
                                    <div style="display: flex; justify-content: center;">
                                        <form action="${pageContext.request.contextPath}/lecture/${lectureId}/note/${note.noteId}/downloadAttachments/${attachment.attachmentId}" method="GET" style="margin-right: 10px;">
                                            <input type="submit" class="list-btn" value="${attachment.filename}" aria-label="Attachment: ${attachment}"/>
                                        </form>
                                        <security:authorize access="hasRole('TEACHER')">
                                            <form action="${pageContext.request.contextPath}/lecture/${lectureId}/note/${note.noteId}/deleteAttachments/${attachment.attachmentId}" method="GET">
                                                <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label="Delete Attachments: ${attachment}"/>
                                            </form>
                                        </security:authorize>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
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