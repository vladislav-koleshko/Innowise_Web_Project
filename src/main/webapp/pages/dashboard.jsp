<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.inkspac3.course.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/dashboard.css">
</head>
<body>

<header class="header">
    <div class="logo">AUDIO</div>

    <a class="user-link" href="${pageContext.request.contextPath}/orders">
        <span class="username"><%= user.getName() %></span>
    </a>
</header>

<main class="container">
    <h1>Доступные биты</h1>

    <div class="beats-grid">

        <!-- Beat card -->
        <div class="beat-card">
            <div class="beat-info">
                <h3>TEST1</h3>
                <p class="author">by inkspac3</p>
                <span class="price">$15</span>
            </div>

            <audio controls>
                <source src="${pageContext.request.contextPath}main/resources/audio/dark_trap.mp3" type="audio/mpeg">
            </audio>

            <button class="buy-btn">Купить</button>
        </div>

        <div class="beat-card">
            <div class="beat-info">
                <h3>TEST2</h3>
                <p class="author">by inkspac3</p>
                <span class="price">$20</span>
            </div>

            <audio controls>
                <source src="${pageContext.request.contextPath}main/resources/audio/dark_trap.mp3" type="audio/mpeg">
            </audio>

            <button class="buy-btn">Купить</button>
        </div>

    </div>
</main>

</body>
</html>
