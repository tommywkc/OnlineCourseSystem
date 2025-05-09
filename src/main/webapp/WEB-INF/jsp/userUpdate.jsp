<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="zh-HK">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/userUpdate.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" type="text/css">
  <title>Online Learning System</title>

</head>
<body>
<jsp:include page="header.jsp" />


<div class="container-fluid">
  <!-- Course Name Section -->

  <div class="login-container">

    <h2><spring:message code="editUserProfile"/></h2>
    <form:form method="POST" enctype="multipart/form-data" modelAttribute="userUpdate">

      <form:label path="username"><spring:message code="userName"/> </form:label><br/>
      <div class="input-group">
        <form:input type="text" path="username" readonly="true" />
      </div>

      <form:label path="fullname"><spring:message code="fullName"/></form:label><br/>
      <form:errors path="fullname" cssClass="error" />
      <div class="input-group">
        <form:input type="text" path="fullname"/>
      </div>

      <form:label path="password"><spring:message code="newPasswordIfNeed"/></form:label><br/>
      <form:errors path="password" cssClass="error" />
    <div class="input-group">
        <form:input type="text" path="password"/>
    </div>


      <form:label path="phoneNumber"><spring:message code="phoneNumber"/></form:label><br/>
      <form:errors path="phoneNumber" cssClass="error" />
      <div class="input-group">
        <form:input type="text" path="phoneNumber"/>
      </div>

      <form:label path="email"><spring:message code="email"/></form:label><br/>
      <form:errors path="email" cssClass="error" />
      <div class="input-group">
        <form:input type="text" path="email"/>
      </div>

      <security:authorize access="hasRole('TEACHER')">
        <form:label path="role"><spring:message code="role"/></form:label><br/>
        <form:errors path="role" cssClass="error" />
        <form:radiobutton path="role" value="ROLE_STUDENT"/>Student
        <form:radiobutton path="role" value="ROLE_TEACHER"/>Teacher
      </security:authorize>

      <security:authorize access="hasRole('STUDENT')">
        <form:label path="role">Roles</form:label><br/>
        <form:errors path="role" cssClass="error" />
        <form:radiobutton path="role" value="ROLE_STUDENT"/>Student
      </security:authorize>

      <br/><br/>


      <button type="submit" class="btn login-btn" value="login"><spring:message code="button.edit"/></button>
    </form:form>
  </div>

</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
