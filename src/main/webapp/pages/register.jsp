<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация — АудиоТреки</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="auth-container">
    <h2>Регистрация</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/register">
        <label for="username">Имя пользователя</label>
        <input type="text" id="username" name="username" placeholder="Ваш логин" required>

        <label for="email">E-mail</label>
        <input type="email" id="email" name="email" placeholder="example@mail.com" required>

        <label for="password">Пароль</label>
        <input type="password" id="password" name="password" placeholder="Введите пароль" required>

        <label for="confirmPassword">Повторите пароль</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Повтор пароля" required>

        <button type="submit" class="btn">Создать аккаунт</button>
    </form>

    <div class="links">
        Уже есть аккаунт? <a href="login.jsp">Войти</a>
    </div>
</div>
</body>
</html>