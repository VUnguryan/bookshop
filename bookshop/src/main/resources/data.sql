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

INSERT INTO Books
(id, name,  year,  price, rate, create_date, background)
VALUES
(1, 'Java: Руководство для начинающих', 2014, 90000, 5.0, now(), 'https://chytayka.com.ua/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/7/9/79650.jpg'),
(2, 'Грамматика: сборник упражнений',  2002, 30000, 5.0, now(), 'http://ost-nvo.com.ua/wp-content/uploads/2015/04/zno_angliyska1.jpg'),
(3, 'Случайная вакансия',  2016, 12000, 1.1, now(), 'https://i1.rozetka.ua/goods/2140869/azbuka_9785389079878_images_2140869641.jpg'),
(4, 'Harry Potter and the Philosophers Stone',  2014,  45000, 5.0,  now(), 'https://i2.rozetka.ua/goods/12099178/54829080_images_12099178356.jpg'),
(5, 'Sapiens. Краткая история человечества',  2002,15000, 4.1,  now(), 'https://i2.rozetka.ua/goods/11494291/78677876_images_11494291616.jpg'),
(6, 'Хранители', 2012,  78000, 5.0,  now(), 'https://i1.rozetka.ua/goods/1417882/6498520_images_1417882113.jpg'),
(7, 'Атлант розправив плечі. Комплект з трьох книг у футлярі',  2002,  142000, 3.0,  now(), 'https://i2.rozetka.ua/goods/11294607/nash_format_9786177279357_images_11294607738.jpg'),
(8, 'Гравити Фолз. ежеДНЕВНИК',  2002,  33000, 2.0,  now(), 'https://i1.rozetka.ua/goods/10775113/71496220_images_10775113012.jpg'),
(9, 'Зеленая миля',  2002,130000, 2.8,  now(), 'https://i1.rozetka.ua/goods/1412822/6496168_images_1412822470.jpg'),
(10, 'Шантарам',  2002, 3000, 5.0,  now(), 'https://i2.rozetka.ua/goods/8942912/63063558_images_8942912781.jpg');

