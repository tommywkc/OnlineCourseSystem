<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
  <style>
    .error {
      color: red;
      font-weight: bold;
      display: block;
    }
  </style>
</head>
<body>

<jsp:include page="header.jsp" />


<div class="container-fluid">
  <div class="login-container">

    <h1 class="login-title"><spring:message code="button.addPoll"/></h1>



    <form:form method="POST" modelAttribute="pollAdd">

      <form:label path="question"><spring:message code="question"/></form:label><br/>
      <form:errors path="question" cssClass="error" />
    <div class="input-group">
      <form:input type="text" path="question"/>
    </div>

      <form:label path="choice1"><spring:message code="choice"/>1</form:label><br/>
      <form:errors path="choice1" cssClass="error" />
    <div class="input-group">
      <form:input type="text" path="choice1"/>
    </div>

      <form:label path="choice2"><spring:message code="choice"/>2</form:label><br/>
      <form:errors path="choice2" cssClass="error" />
    <div class="input-group">
      <form:input type="text" path="choice2"/>
    </div>

      <form:label path="choice3"><spring:message code="choice"/>3</form:label><br/>
      <form:errors path="choice3" cssClass="error" />
    <div class="input-group">
      <form:input type="text" path="choice3"/>
    </div>

      <form:label path="choice4"><spring:message code="choice"/>4</form:label><br/>
      <form:errors path="choice4" cssClass="error" />
    <div class="input-group">
      <form:input type="text" path="choice4"/>
    </div>



      <button id="login-button" type="submit"><spring:message code="button.add"/></button>
    </form:form>
  </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>