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

    <form action="register" method="post">
        <label for="username">Имя пользователя</label>
        <input type="text" id="username" name="username" placeholder="Ваш логин" required>

        <label for="email">E-mail</label>
        <input type="email" id="email" name="email" placeholder="example@mail.com" required>

        <label for="password">Пароль</label>
        <input type="password" id="password" name="password" placeholder="Введите пароль" required>

        <label for="password2">Повторите пароль</label>
        <input type="password" id="password2" name="password2" placeholder="Повтор пароля" required>

        <button type="submit" class="btn">Создать аккаунт</button>
    </form>

    <div class="links">
        Уже есть аккаунт? <a href="auth.jsp">Войти</a>
    </div>
</div>
</body>
</html>