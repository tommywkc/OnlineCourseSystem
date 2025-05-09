


<c:set var="currentLang" value="${empty sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] ? 'en-US' : sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'].toLanguageTag()}" />

<!-- Header -->
<div class="header mb-4">
  <div class="logo">
    <a href="${pageContext.request.contextPath}/index" style="text-decoration: none; color: inherit;">
      <img class="logo" src="${pageContext.request.contextPath}/images/logo.png" alt="logo" width="50"/>

      <span class="title h3"><spring:message code="system.name"/></span>
    </a>
  </div>
  <div class="ml-auto">

    <security:authorize access="isAuthenticated()">
      <form action="${pageContext.request.contextPath}/commentHistory" method="GET" style="display: inline;">
        <button type="submit" class="btn login-btn"><spring:message code="comment"/><spring:message code="button.history"/></button>
      </form>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
      <form action="${pageContext.request.contextPath}/pollHistory" method="GET" style="display: inline;">
        <button type="submit" class="btn login-btn"><spring:message code="poll"/><spring:message code="button.history"/></button>
      </form>
    </security:authorize>

    <div class="btn-group" style="margin-right: 10px;">
      <form id="langForm" action="${pageContext.request.contextPath}/changeLanguage" method="GET">
        <select name="lang" id="langSelect" onchange="handleLanguageChange(this)" class="form-control">
          <option value="en_US" ${currentLang == 'en-US' ? 'selected' : ''}>English</option>
          <option value="zh_HK" ${currentLang == 'zh-HK' ? 'selected' : ''}>繁體中文</option>
          <option value="zh_CN" ${currentLang == 'zh-CN' ? 'selected' : ''}>简体中文</option>
        </select>
      </form>
    </div>



    <security:authorize access="hasRole('TEACHER')">
            <form action="${pageContext.request.contextPath}/user/list" method="GET" style="display: inline;">
              <button type="submit" class="btn btn-warning">
                <spring:message code="button.edit"/>
              </button>
            </form>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
      <form action="${pageContext.request.contextPath}/user/update"  method="GET" style="display: inline;">
        <button type="submit" class="btn login-btn">
          <spring:message code="button.update"/>
        </button>
      </form>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
      <form action="${pageContext.request.contextPath}/logout" method="POST" style="display: inline;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit" id="logout-button" class="btn btn-danger"  value="logout">
          <spring:message code="button.logout"/>
        </button>
      </form>
    </security:authorize>

  <security:authorize access="isAnonymous()">
        <form action="${pageContext.request.contextPath}/login" method="GET" style="display: inline;">
          <button type="submit" class="btn login-btn">
            <spring:message code="button.login"/>
          </button>
        </form>
  </security:authorize>

  </div>
</div>





<script>
  function handleLanguageChange(selectElement) {
    const form = document.getElementById('langForm');
    form.submit();
  }

</script>