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


INSERT INTO Authors (id, name, biography) VALUES (1, 'Author 1','biography text');
INSERT INTO Authors (id, name, biography) VALUES (2, 'Author 2','biography text');
INSERT INTO Authors (id, name, biography) VALUES (3, 'Author 3','biography text');
INSERT INTO Authors (id, name, biography) VALUES (4, 'Author 4','biography text');

INSERT INTO Books
(id, name,  year,  price, rate, create_date, background)
VALUES
(1, 'Java: Руководство для начинающих', 2014, 90000, 5.0, now(), '79650.jpg'),
(2, 'Грамматика: сборник упражнений',  2002, 30000, 5.0, now(), 'zno_angliyska1.jpg');