<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Личный кабинет — АудиоТреки</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/styles.css">
</head>
<body>
<div class="container">
    <h2>Личный кабинет</h2>

    <c:if test="${not empty message}">
        <div class="success">${message}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/updateProfile" method="post">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="${sessionScope.user.email}" required>

        <label for="password">Новый пароль</label>
        <input type="password" id="password" name="password" placeholder="Введите новый пароль">

        <label for="confirmPassword">Подтвердите пароль</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Подтвердите новый пароль">

        <button type="submit" class="btn">Сохранить изменения</button>
    </form>

    <div class="links">
        <a href="${pageContext.request.contextPath}/pages/dashboard.jsp">Назад на дашборд</a>
    </div>
</div>
</body>
</html>