<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация — АудиоТреки</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Inter, Arial, sans-serif;
            background: linear-gradient(180deg, #061025 0%, #0b1320 70%);
            color: #e6eef6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .auth-container {
            width: 360px;
            padding: 32px;
            background: rgba(255,255,255,0.03);
            border-radius: 16px;
            box-shadow: 0 0 20px rgba(0,0,0,0.3);
            backdrop-filter: blur(8px);
        }
        h2 {
            margin: 0 0 20px 0;
            text-align: center;
        }
        label {
            display: block;
            margin-top: 14px;
            font-size: 14px;
            color: #9aa4b2;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            border-radius: 10px;
            border: 1px solid rgba(255,255,255,0.08);
            background: transparent;
            color: #e6eef6;
            margin-top: 6px;
        }
        .btn {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background: linear-gradient(90deg, #6ee7b7, #3b82f6);
            border: none;
            border-radius: 10px;
            font-weight: 600;
            cursor: pointer;
            color: #032018;
        }
        .links {
            margin-top: 18px;
            text-align: center;
            font-size: 14px;
        }
        .links a {
            color: #6ee7b7;
            text-decoration: none;
        }
    </style>
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