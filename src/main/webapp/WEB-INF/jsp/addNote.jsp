<!DOCTYPE html>
<html lang="zh-HK" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
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

        <h1 class="login-title"><spring:message code="createNewNote"/></h1>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="noteForm">
            <form:label path="noteName"><spring:message code='newnotename'/>:</form:label><br/>
        <div class="input-group">
            <form:input type="text" path="noteName"/><br/><br/>
        </div>
            <b><spring:message code='selectfile'/></b><br/>
            <input type="file" name="attachments" multiple="multiple"/><br/><br/>
            <input type="submit" class="btn login-btn" value="<spring:message code='button.addNote'/>" aria-label="Add Note"/>
        </form:form>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>