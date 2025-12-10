<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Страница не найдена</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/notfound.css">
</head>
<body>
<div class="container">
    <h1>404</h1>
    <h2>Страница не найдена</h2>
    <p>Похоже, вы попали не туда. Возможно, страница была удалена или перемещена.</p>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться на главную</a>
</div>
</body>
</html>