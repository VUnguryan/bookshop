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
(id, name, author_id, year, genre_id, publisher_id, price, rate, admin_id, create_date, background) 
VALUES 
(1, 'Java: Руководство для начинающих',1, 2014, 1, 6, 90000, 5.0, 1, now(), 'https://chytayka.com.ua/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/7/9/79650.jpg'),
(2, 'Грамматика: сборник упражнений',1,  2002, 1, 6, 30000, 5.0, 1, now(), 'http://ost-nvo.com.ua/wp-content/uploads/2015/04/zno_angliyska1.jpg'),
(3, 'Случайная вакансия',1,  2016, 1, 6, 12000, 1.1, 1, now(), 'https://i1.rozetka.ua/goods/2140869/azbuka_9785389079878_images_2140869641.jpg'),
(4, 'Harry Potter and the Philosophers Stone',1,  2014, 2, 6, 45000, 5.0, 1, now(), 'https://i2.rozetka.ua/goods/12099178/54829080_images_12099178356.jpg'),
(5, 'Sapiens. Краткая история человечества',1,  2002, 1, 6, 15000, 4.1, 1, now(), 'https://i2.rozetka.ua/goods/11494291/78677876_images_11494291616.jpg'),
(6, 'Хранители',1,  2012, 1, 6, 78000, 5.0, 1, now(), 'https://i1.rozetka.ua/goods/1417882/6498520_images_1417882113.jpg'),
(7, 'Атлант розправив плечі. Комплект з трьох книг у футлярі',1,  2002, 1, 6, 142000, 3.0, 1, now(), 'https://i2.rozetka.ua/goods/11294607/nash_format_9786177279357_images_11294607738.jpg'),
(8, 'Гравити Фолз. ежеДНЕВНИК',1,  2002, 1, 6, 33000, 2.0, 1, now(), 'https://i1.rozetka.ua/goods/10775113/71496220_images_10775113012.jpg'),
(9, 'Зеленая миля',1,  2002, 1, 6, 130000, 2.8, 1, now(), 'https://i1.rozetka.ua/goods/1412822/6496168_images_1412822470.jpg'),
(10, 'Шантарам',1,  2002, 1, 6, 3000, 5.0, 1, now(), 'https://i2.rozetka.ua/goods/8942912/63063558_images_8942912781.jpg');