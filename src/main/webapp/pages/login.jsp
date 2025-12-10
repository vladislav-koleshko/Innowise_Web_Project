<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация — АудиоТреки</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/styles.css">
</head>
<body>
<div class="auth-container">
    <h2>Вход в систему</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Логин</label>
        <input type="text" id="username" name="username" pattern = "^[A-Za-z0-9_]{4,20}$" title="Только буквы и цифры. Длина должна быть от 4 символов!" placeholder="Введите логин" required>

        <label for="password">Пароль</label>
        <input type="password" id="password" name="password" pattern = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\S+$).{8,}$" title="Минимум одна буква, цифра и спецсимвол. Длина пароля должна быть от 8 символов!" placeholder="Введите пароль" required>

        <button type="submit" class="btn">Войти</button>
    </form>

    <div class="links">
        Нет аккаунта? <a href="register.jsp">Регистрация</a>
    </div>
</div>
</body>
</html>