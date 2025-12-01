<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация — АудиоТреки</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="auth-container">
    <h2>Вход в систему</h2>

    <form action="login" method="post">
        <label for="login">Логин</label>
        <input type="text" id="login" name="login" placeholder="Введите логин" required>

        <label for="password">Пароль</label>
        <input type="password" id="password" name="password" placeholder="Введите пароль" required>

        <button type="submit" class="btn">Войти</button>
    </form>

    <div class="links">
        Нет аккаунта? <a href="register.jsp">Регистрация</a>
    </div>
</div>
</body>
</html>