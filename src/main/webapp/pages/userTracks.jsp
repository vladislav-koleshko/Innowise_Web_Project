<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Панель — АудиоТреки</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/userprofile.css">
</head>
<body>
<div class="container">
    <aside class="sidebar">
        <div class="brand">
            <div class="logo">AT</div>
            <div>
                <div style="font-weight:700">АудиоТреки</div>
                <div style="font-size:12px;color:var(--muted)">Личный кабинет пользователя</div>
            </div>
        </div>
        <nav>
            <div class="back-link">
                <a href="${pageContext.request.contextPath}/pages/dashboard.jsp">← На главную</a>
            </div>
            <a href="${pageContext.request.contextPath}/orders">Мои заказы</a>
            <a href="${pageContext.request.contextPath}/pages/userTracks.jsp" class="active">Мои треки</a>
            <a href="${pageContext.request.contextPath}/pages/changeUserProfile.jsp">Личный кабинет</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn-danger">Выйти</a>
        </nav>
        <hr style="border:0;border-top:1px solid rgba(255,255,255,0.02);margin:14px 0">
        <div style="font-size:13px;color:var(--muted)">Баланс: <strong style="color:var(--accent)">500 $</strong></div>
    </aside>

    <main class="main">
        <header class="card">
            <div>
                <h2 style="margin:0">Добро пожаловать, ${sessionScope.user.name}</h2>
                <div style="font-size:13px;color:var(--muted)">Ваши треки и заказы</div>
            </div>
            <div class="search">
                <input placeholder="Поиск трека или заказа..." />
                <button class="btn ghost">Поиск</button>
            </div>
        </header>

        <section class="grid-1">
            <div id="myTracks" class="card" style="margin-top:12px">
                <h3>Мои треки</h3>
                <div class="list">
                    <c:forEach var="track" items="${tracks}">
                        <div style="padding:12px;border-radius:8px;background:rgba(255,255,255,0.01);margin-bottom:8px">
                            <div style="font-weight:600">${track.title} — ${track.artist}</div>
                            <div class="meta" style="color:var(--muted);">${track.duration} • ${track.price} $</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </main>
</div>
</body>
</html>