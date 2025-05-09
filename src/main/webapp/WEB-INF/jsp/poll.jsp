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
      <h2 class="course-title"><spring:message code="question"/>:${poll.question}</h2>
    </div>
  </div>

  <!-- Lecture and Poll Section -->
  <div class="row">
    <div class="col-md-6 col-12">
      <div class="lecture-container">
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h2 class="poll-title"><spring:message code="selectYourAnswer"/></h2>
        </div>
        <form action="${pageContext.request.contextPath}/poll/submit/${pollId}/" method="GET" style="margin-left: auto;">
          <c:forEach items="${pollAnswers}" var="answer" varStatus="status">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
              <div style="display: flex; align-items: center;">
                <input type="radio" name="selectedOption" value="${answer}" id="${answer}" required />
                <label for="${answer}" style="margin-left: 8px;">${answer}</label>
              </div>
              <i>(${answerCount[status.index]} <spring:message code="votes"/>)</i>
            </div>
            <br>
          </c:forEach>
          <button type="submit" class="btn login-btn"> <spring:message code="uploadAnswer"/></button>
        </form>
      </div>
    </div>



    <div class="col-md-6 col-12">
      <div class="poll-container">
        <h2 class="poll-title"><spring:message code="comment"/></h2>
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
                      <form action="${pageContext.request.contextPath}/poll/${pollId}/deleteComment/${comment.pcId}" method="GET">
                        <input type="submit" class="btn btn-danger" value="<spring:message code="button.delete"/>" aria-label="Delete Comment: ${comment}"/>
                      </form>
                    </security:authorize>
                  </div>
                </li>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </ul>
        <form action="${pageContext.request.contextPath}/poll/${pollId}/addComment" method="GET">
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

<script>
  function highlightLabel(radio) {
    // 清除所有标签的高亮
    const labels = document.querySelectorAll('.list-btn');
    labels.forEach(label => {
      label.style.color = ''; // 恢复原始颜色
      label.style.backgroundColor = ''; // 恢复原始背景色
    });

    // 设置选中项的标签颜色
    const selectedLabel = radio.nextElementSibling; // 获取对应的标签
    selectedLabel.style.color = '#FFFFFF'; // 选中时的文字颜色（可以根据需要调整）
    selectedLabel.style.backgroundColor = '#003270'; // 选中时的背景色（可以根据需要调整）
  }
</script>
</body>
</html>