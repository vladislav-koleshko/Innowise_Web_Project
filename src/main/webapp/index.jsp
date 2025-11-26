<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>АудиоТреки — Главная</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Inter, Arial, sans-serif;
            background: #060d1d;
            color: #e6eef6;
        }

        header {
            background: rgba(255,255,255,0.03);
            padding: 20px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            backdrop-filter: blur(6px);
            border-bottom: 1px solid rgba(255,255,255,0.05);
        }

        .logo {
            font-size: 24px;
            font-weight: 700;
            color: #6ee7b7;
        }

        nav a {
            color: #9aa4b2;
            text-decoration: none;
            margin-left: 20px;
            font-size: 15px;
        }

        nav a:hover {
            color: #6ee7b7;
        }

        .hero {
            padding: 80px 40px;
            text-align: center;
            background: linear-gradient(180deg, #081124 0%, #060d1d 80%);
        }

        .hero h1 {
            margin: 0;
            font-size: 42px;
            font-weight: 700;
        }

        .hero p {
            max-width: 700px;
            margin: 20px auto 0 auto;
            font-size: 18px;
            color: #9aa4b2;
        }

        .btn-main {
            display: inline-block;
            margin-top: 30px;
            padding: 14px 26px;
            background: linear-gradient(90deg, #6ee7b7, #3b82f6);
            border-radius: 12px;
            text-decoration: none;
            color: #032018;
            font-weight: 600;
            font-size: 16px;
        }

        .section {
            padding: 60px 40px;
            max-width: 1000px;
            margin: 0 auto;
        }

        .section h2 {
            font-size: 30px;
            margin-bottom: 20px;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }

        .card {
            padding: 20px;
            background: rgba(255,255,255,0.03);
            border-radius: 14px;
            border: 1px solid rgba(255,255,255,0.04);
        }

        .card h3 {
            margin-top: 0;
            font-size: 20px;
            color: #6ee7b7;
        }

        .card p {
            color: #9aa4b2;
            font-size: 14px;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #6c7785;
            margin-top: 40px;
            border-top: 1px solid rgba(255,255,255,0.05);
            background: rgba(255,255,255,0.02);
        }
    </style>
</head>
<body>
<!-- HEADER -->
<header>
    <div class="logo">AUDIO TRACKS</div>
    <nav>
        <a href="index.jsp">Главная</a>
        <a href="pages/auth.jsp">Войти</a>
    </nav>
</header>

<!-- HERO BLOCK -->
<section class="hero">
    <h1>Онлайн-сервис продажи и создания аудиотреков</h1>
    <p>
        Заказывайте кастомные аудио-треки, оформляйте покупки, получайте доступ к
        уникальным сборкам и альбомам. Управление клиентами, бонусами и скидками для администраторов.
    </p>

    <a class="btn-main" href="pages/auth.jsp">Перейти к покупкам</a>
</section>

<!-- ABOUT -->
<section class="section">
    <h2>О проекте</h2>
    <p>
        AUDIO TRACKS — это универсальная онлайн-платформа для покупки, заказа и управления
        аудиотреками. Клиенты могут просматривать доступные треки, оставлять отзывы и
        оформлять заказы, а администраторы — добавлять новый контент, управлять пользователями,
        формировать сборки и назначать бонусные программы.
    </p>
</section>

<!-- SERVICES -->
<section class="section">
    <h2>Наши услуги</h2>

    <div class="cards">
        <div class="card">
            <h3>Заказ аудиотреков</h3>
            <p>Индивидуальные треки под ваши нужды: музыкальные заставки, фоновые композиции и многое другое.</p>
        </div>

        <div class="card">
            <h3>Покупка существующих треков</h3>
            <p>Большая библиотека готовых аудиокомпозиций, доступная для мгновенной покупки.</p>
        </div>

        <div class="card">
            <h3>Управление альбомами</h3>
            <p>Добавление, редактирование и продажа альбомов из множества треков.</p>
        </div>

        <div class="card">
            <h3>Сборки и акции</h3>
            <p>Создание промо-наборов с уникальными скидками для клиентов.</p>
        </div>
    </div>
</section>

<!-- FOOTER -->
<footer>
    © 2025 AUDIO TRACKS — Все права защищены.
</footer>
</body>
</html>