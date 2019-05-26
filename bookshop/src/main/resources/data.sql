INSERT INTO Roles(id, roles) VALUES (1,'admin');
INSERT INTO Roles(id, roles) VALUES (2,'user');

INSERT INTO Users (id, login, password) VALUES (1, 'admin', 'admin');
INSERT INTO Users (id, login, password) VALUES (2, 'user', 'user');

INSERT INTO users_has_roles(users_id, roles_id) VALUES (2, 2);
INSERT INTO users_has_roles(users_id, roles_id) VALUES (1, 1);


INSERT INTO Genres (id, name) VALUES (1, 'Романтика');
INSERT INTO Genres (id, name) VALUES (2, 'Фантастика');
INSERT INTO Genres (id, name) VALUES (3, 'Ужасы');
INSERT INTO Genres (id, name) VALUES (4, 'Сказки');
INSERT INTO Genres (id, name) VALUES (5, 'Публицистика');
INSERT INTO Genres (id, name) VALUES (6, 'Учебники');
INSERT INTO Genres (id, name) VALUES (7, 'Научная');
INSERT INTO Genres (id, name) VALUES (8, 'Научно-популярная');


INSERT INTO Publishers (id, name) VALUES (1, 'Эксмо');
INSERT INTO Publishers (id, name) VALUES (2, 'Махаон');
INSERT INTO Publishers (id, name) VALUES (3, 'Москва');
INSERT INTO Publishers (id, name) VALUES (4, 'Омск');

INSERT INTO Authors 
(id, name, biography) 
VALUES 
(1, 'Герберт Шилдт', 'Шилдт — писатель и учёный в сфере компьютерных наук, получил образование и учёную степень в Иллинойском университете в Урбана-Шампейн. 
Был членом комитета ANSI, который принимал стандарты С в 1989 году и комитета ISO, принимавшего стандарты C++ в 1998.' ),
(2, 'Дж.К.Роулинг', 'Джоан Роулинг, известная под псевдонимами Дж. К. Роулинг и Роберт Гэлбрейт, — британская писательница, сценаристка и кинопродюсер, 
наиболее известная как автор серии романов о Гарри Поттере.' ),
(3, 'Юваль Ной Харари', 'Юваль Ной Харари родился 24 февраля 1976 года в городе Кирьят-Ата (Израиль), в еврейской семье ливанского и 
восточно-европейского происхождения.' ),
(4, 'Алан Мур', 'Алан Мур (англ. Alan Moore, род. 18 ноября 1953) — английский писатель, известный, прежде всего, работой в комиксах.' ),
(5, 'Айн Ренд', 'Алиса Зиновьевна Розенбаум родилась в Санкт-Петербурге.' ),
(6, 'Грегори Роберт Дэвис', 'Робертсон Дэвис — канадский писатель-прозаик, драматург, критик, журналист.' ),
(7, 'Стивен Кинг', 'ти́вен Э́двин Кинг  — американский писатель, работающий в разнообразных жанрах, включая ужасы, триллер, фантастику, фэнтези, мистику, драму; получил прозвище «Король ужасов».' ),
(8, 'Серге́й Алекса́ндрович Есе́нин', '— русский поэт, представитель новокрестьянской поэзии и лирики, а в более позднем периоде творчества — имажинизма.' ),
(9, 'Алекса́ндр Алекса́ндрович Блок', '— русский поэт, писатель, публицист, драматург, переводчик, литературный критик. Классик русской литературы XX столетия, один из крупнейших представителей русского символизма.' ),
(10, 'Алекса́ндр Серге́евич Пу́шкин', ' русский поэт, драматург и прозаик, заложивший основы русского реалистического направления[7], критик и теоретик литературы, историк, публицист.' );

INSERT INTO Books 
(id, name, year, price, rate, create_date, background)
VALUES 
(1, 'Java: Руководство для начинающих', 2014, 90000, 5.0,  now(), '79650.jpg'),
(2, 'Грамматика: сборник упражнений', 2002, 30000, 5.0, now(), 'zno_angliyska1.jpg'),
(3, 'Случайная вакансия', 2016,  12000, 1.1, now(), 'azbuka_9785389079878_images_2140869641.jpg'),
(4, 'Harry Potter and the Philosophers Stone', 2014, 45000, 5.0, now(), '54829080_images_12099178356.jpg'),
(5, 'Sapiens. Краткая история человечества', 2002, 15000, 4.1, now(), '78677876_images_11494291616.jpg'),
(6, 'Хранители', 2012, 78000, 5.0, now(), '6498520_images_1417882113.jpg'),
(7, 'Атлант розправив плечі. Комплект з трьох книг у футлярі',  2002,  142000, 3.0, now(), 'nash_format_9786177279357_images_11294607738.jpg'),
(8, 'Гравити Фолз. ежеДНЕВНИК', 2002,  33000, 2.0, now(), '71496220_images_10775113012.jpg'),
(9, 'Зеленая миля', 2002,  130000, 2.8, now(), '6496168_images_1412822470.jpg'),
(10, 'Шантарам', 2002,  3000, 5.0,  now(), '63063558_images_8942912781.jpg');