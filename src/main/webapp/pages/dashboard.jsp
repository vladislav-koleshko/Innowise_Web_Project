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
    <style>
        :root{
            --bg:#0f1724; --card:#0b1220; --accent:#6ee7b7; --muted:#9aa4b2; --glass: rgba(255,255,255,0.03);
            --radius:12px; --gap:16px; font-family: Inter, Roboto, Arial, sans-serif;
        }
        *{box-sizing:border-box}
        body{margin:0; background:linear-gradient(180deg,#061025 0%, #0b1320 60%); color:#e6eef6;}
        .container{max-width:1200px;margin:32px auto;padding:24px;display:grid;grid-template-columns:320px 1fr;gap:24px}

        /* SIDEBAR */
        .sidebar{background:var(--card);padding:18px;border-radius:var(--radius);backdrop-filter: blur(6px);}
        .brand{display:flex;align-items:center;gap:10px;margin-bottom:12px}
        .logo{width:44px;height:44px;border-radius:10px;background:linear-gradient(135deg,var(--accent),#3b82f6);display:flex;align-items:center;justify-content:center;font-weight:700}
        nav a{display:block;color:var(--muted);padding:10px;border-radius:8px;text-decoration:none;margin:6px 0}
        nav a.active{background:var(--glass);color:var(--accent)}

        /* MAIN */
        .main{display:grid;grid-template-rows:auto 1fr;gap:18px}
        header.card, .card{background:linear-gradient(180deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01));padding:18px;border-radius:var(--radius)}
        header.card{display:flex;align-items:center;justify-content:space-between}
        .search{display:flex;gap:8px;align-items:center}
        .search input{background:transparent;border:1px solid rgba(255,255,255,0.05);padding:8px 12px;border-radius:10px;color:inherit}

        .grid-2{display:grid;grid-template-columns:1fr 360px;gap:16px}
        .forms{display:flex;flex-direction:column;gap:12px}

        label{display:block;font-size:13px;color:var(--muted);margin-bottom:6px}
        input[type=text], input[type=number], select, textarea{width:100%;padding:10px;border-radius:8px;border:1px solid rgba(255,255,255,0.05);background:transparent;color:inherit}
        textarea{min-height:100px}
        .btn{display:inline-block;padding:10px 14px;border-radius:10px;background:linear-gradient(90deg,var(--accent),#3b82f6);color:#032018;font-weight:600;border:none;cursor:pointer}
        .btn.ghost{background:transparent;border:1px solid rgba(255,255,255,0.06);color:var(--accent)}

        .list{max-height:360px;overflow:auto;padding-right:8px}
        .track{padding:12px;border-radius:10px;background:rgba(255,255,255,0.01);margin-bottom:8px;display:flex;justify-content:space-between;align-items:center}
        .meta{font-size:13px;color:var(--muted)}

        /* responsive */
        @media (max-width:900px){.container{grid-template-columns:1fr;}.grid-2{grid-template-columns:1fr}}
    </style>
</head>
<body>
<div class="container">
    <aside class="sidebar">
        <div class="brand">
            <div class="logo">AT</div>
            <div>
                <div style="font-weight:700">АудиоТреки</div>
                <div style="font-size:12px;color:var(--muted)">Панель заказов и администрирования</div>
            </div>
        </div>
        <nav>
            <a href="#orders" class="active">Заказы</a>
            <a href="#tracks">Треки</a>
            <a href="#albums">Альбомы</a>
            <a href="#assemblies">Сборки</a>
            <a href="#clients">Клиенты</a>
            <a href="#reviews">Отзывы</a>
        </nav>
        <hr style="border:0;border-top:1px solid rgba(255,255,255,0.02);margin:14px 0">
        <div style="font-size:13px;color:var(--muted)">Баланс: <strong style="color:var(--accent)">1,240.50 ₽</strong></div>
    </aside>

    <main class="main">
        <header class="card">
            <div>
                <h2 style="margin:0">Панель управления — Заказы и треки</h2>
                <div style="font-size:13px;color:var(--muted)">Рабочее пространство администратора / клиента</div>
            </div>
            <div class="search">
                <input placeholder="Поиск трека, альбома, клиента..." />
                <button class="btn ghost">Поиск</button>
            </div>
        </header>

        <section class="grid-2">
            <!-- LEFT: ФОРМЫ И СПИСОК -->
            <div>
                <!-- Заказчик: Оформление заказа -->
                <div id="orders" class="card forms">
                    <h3>Оформить заказ</h3>
                    <!-- В реальном приложении треки/альбомы подставляются из модели -->
                    <form action="/order" method="post">
                        <label for="clientName">Имя клиента</label>
                        <input id="clientName" name="clientName" type="text" placeholder="Иван Иванов" required />

                        <label for="trackSelect">Выбрать трек/альбом</label>
                        <select id="trackSelect" name="trackId">
                            <c:forEach var="t" items="${tracks}">
                                <option value="${t.id}">${t.title} — ${t.artist}</option>
                            </c:forEach>
                        </select>

                        <label for="qty">Количество</label>
                        <input id="qty" name="quantity" type="number" min="1" value="1" />

                        <label for="note">Комментарий</label>
                        <textarea id="note" name="note" placeholder="Особые пожелания по треку..."></textarea>

                        <div style="display:flex;gap:8px;margin-top:8px">
                            <button class="btn" type="submit">Оплатить и заказать</button>
                            <button class="btn ghost" type="button">Добавить в корзину</button>
                        </div>
                    </form>
                </div>

                <!-- Отзывы -->
                <div id="reviews" class="card" style="margin-top:12px">
                    <h3>Отзывы клиентов</h3>
                    <div class="list">
                        <c:forEach var="r" items="${reviews}">
                            <div style="padding:12px;border-radius:8px;background:rgba(255,255,255,0.01);margin-bottom:8px">
                                <div style="font-weight:600">${r.userName} <span style="font-weight:400;color:var(--muted);font-size:13px"> — ${r.date}</span></div>
                                <div style="margin-top:6px;color:var(--muted)">${r.text}</div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!-- RIGHT: АДМИН ПАНЕЛЬ -->
            <aside>
                <div id="tracks" class="card">
                    <h3>Треки — управление</h3>
                    <div style="margin-bottom:10px;color:var(--muted)">Добавить новый трек:</div>
                    <form action="/admin/track/add" method="post" enctype="multipart/form-data">
                        <label>Название</label>
                        <input name="title" type="text" placeholder="Название трека" />
                        <label>Артист</label>
                        <input name="artist" type="text" placeholder="Исполнитель" />
                        <label>Цена (₽)</label>
                        <input name="price" type="number" min="0" step="0.01" />
                        <label>Файл</label>
                        <input name="file" type="file" />
                        <div style="display:flex;gap:8px;margin-top:8px">
                            <button class="btn" type="submit">Добавить трек</button>
                            <button class="btn ghost" type="button">Импорт CSV</button>
                        </div>
                    </form>

                    <hr style="border:0;border-top:1px solid rgba(255,255,255,0.02);margin:12px 0">
                    <div class="list">
                        <c:forEach var="t" items="${tracks}">
                            <div class="track">
                                <div>
                                    <div style="font-weight:600">${t.title}</div>
                                    <div class="meta">${t.artist} • ${t.duration} • ${t.price} ₽</div>
                                </div>
                                <div style="display:flex;gap:8px">
                                    <a href="/admin/track/edit?id=${t.id}" class="btn ghost">Изменить</a>
                                    <form action="/admin/track/delete" method="post" style="display:inline">
                                        <input type="hidden" name="id" value="${t.id}" />
                                        <button class="btn ghost" type="submit">Удалить</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div id="albums" class="card" style="margin-top:12px">
                    <h3>Альбомы</h3>
                    <form action="/admin/album/add" method="post">
                        <label>Название альбома</label>
                        <input name="albumTitle" type="text" />
                        <label>Треки в альбоме (IDs через запятую)</label>
                        <input name="trackIds" type="text" placeholder="1,5,8" />
                        <div style="display:flex;gap:8px;margin-top:8px">
                            <button class="btn" type="submit">Создать альбом</button>
                        </div>
                    </form>
                </div>

                <div id="assemblies" class="card" style="margin-top:12px">
                    <h3>Сборки (Bundles)</h3>
                    <p style="color:var(--muted);font-size:13px">Формируйте наборы треков для акций и продаж.</p>
                    <form action="/admin/assembly/add" method="post">
                        <label>Название сборки</label>
                        <input name="assemblyTitle" type="text" />
                        <label>Треки (IDs)</label>
                        <input name="assemblyTrackIds" type="text" />
                        <label>Скидка (%)</label>
                        <input name="discount" type="number" min="0" max="100" value="0" />
                        <div style="display:flex;gap:8px;margin-top:8px">
                            <button class="btn" type="submit">Создать</button>
                        </div>
                    </form>
                </div>

                <div id="clients" class="card" style="margin-top:12px">
                    <h3>Клиенты</h3>
                    <div style="font-size:13px;color:var(--muted);margin-bottom:8px">Управление бонусами и скидками</div>
                    <form action="/admin/client/update" method="post">
                        <label>ID клиента</label>
                        <input name="clientId" type="text" />
                        <label>Бонусы (баллы)</label>
                        <input name="bonus" type="number" />
                        <label>Скидка (%)</label>
                        <input name="clientDiscount" type="number" min="0" max="100" />
                        <div style="display:flex;gap:8px;margin-top:8px">
                            <button class="btn" type="submit">Применить</button>
                        </div>
                    </form>
                </div>
            </aside>
        </section>

    </main>
</div>
</body>
</html>
