<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Изменение личных данных</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/styles.css">
</head>
<body>
<div class="container">
    <h2>Изменение личных данных</h2>

    <div class="success" style="${empty message ? 'display:none;' : ''}">
        ${message}
    </div>
    <div class="error" style="${empty error ? 'display:none;' : ''}">
        ${error}
    </div>


    <form action="${pageContext.request.contextPath}/updateProfile" method="post">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="${sessionScope.user.email}" required>

        <label for="password">Новый пароль</label>
        <input type="password" id="password" name="password" pattern = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\S+$).{8,}$" title="Минимум одна буква, цифра и спецсимвол. Длина пароля должна быть от 8 символов!" placeholder="Введите новый пароль">

        <label for="confirmPassword">Подтвердите пароль</label>
        <input type="password" id="confirmPassword" name="confirmPassword" pattern = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\S+$).{8,}$" title="Минимум одна буква, цифра и спецсимвол. Длина пароля должна быть от 8 символов!" placeholder="Подтвердите новый пароль">

        <button type="submit" class="btn">Сохранить изменения</button>
    </form>

    <div class="links">
        <a href="${pageContext.request.contextPath}/orders">Назад</a>
    </div>
</div>
</body>
</html>