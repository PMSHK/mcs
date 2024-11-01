create table if not exists radiation_type
(
    id             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    name           varchar(1000) not null,
    voltage        bigint        not null,
    radiation_exit bigint        not null,
    type           varchar(4)    not null
);

insert into radiation_type (name, radiation_exit, voltage, type)
values ('Рентгенофлюорографичский аппарат с люминисцентным экраном и оптическим переносом изображения, плёночный и цифровой', 1000, 100,'MED'),
       ('Рентгенофлюорографичский малодозовый аппарат со сканирующей линейкой детекторов и цифровой обработкой изображения',2000,100,'MED'),
       ('Рентгенофлюорографичский малодозовый аппарат с УРИ, ПЗС-матрицей и цифровой обработкой изображения',50,100,'MED'),
       ('Рентгенодиагностический аппарат с цифровой обработкой информации',1000,100,'MED'),
       ('Рентнтгенодиагностический комплекс с полным набором штативов (1-е, 2-е, 3-е рабочие места',1000,100,'MED'),
       ('Рентгеновский аппарат для рентгеноскопии (1-е рабочее место-поворотный стол-штатив ПСШ)',1000,100,'MED'),
       ('Рентгеновский аппарат для рентгенографии (2-е и 3-е рабочие места - стол снимков и стойка снимков', 1000,100,'MED'),
       ('Ангиографический комплекс',400,100,'MED'),
       ('Рентгеновский компьютерный томограф',400,125,'MED'),
       ('Хирургический передвижной аппарат с УРИ',200,100,'MED'),
       ('Палатный рентгеновский аппарат',200,90,'MED'),
       ('Рентгеноурологический стол',400,90,'MED'),
       ('Рентгеновский аппарат для литотрипсии',200,90,'MED'),
       ('Маммографический рентгеновский аппарат',200,40,'MED'),
       ('Рентгеновский аппарат для планирования лучевой терапии (симулятор)',200,100,'MED'),
       ('Аппарат для близкодистанционной рентгенотерапии',5000,100,'MED'),
       ('Аппарат для дальнедистанционной рентгенотерапии',12000,250,'MED'),
       ('Остеоденситометр для всего тела',200,0,'MED'),
       ('Остеоденситометр для конечностей',100,70,'MED'),
       ('Остеоденситометр для всего тела и его частей с использованем широкого пучка излучения и двухмерного цифрового детектора',50,0,'MED'),


       ('Дентальный аппарат, работающий с обычной плёнкой без усиливающего экрана',200,70,'DENT'),
       ('Дентальный аппарат и пантомограф, работающие с высокочуствительным плёночным или цифровым приёмником изображения, в том числе визиограф',40,70,'DENT'),
       ('Панорамный аппарат, пантомограф',200,90,'DENT'),


       ('Рентгенофлюорографичский аппарат с люминисцентным экраном и оптическим переносом изображения, плёночный и цифровой', 1000, 100,'VET'),
       ('Рентгенофлюорографичский малодозовый аппарат со сканирующей линейкой детекторов и цифровой обработкой изображения',2000,100,'VET'),
       ('Рентгенофлюорографичский малодозовый аппарат с УРИ, ПЗС-матрицей и цифровой обработкой изображения',50,100,'VET'),
       ('Рентгенодиагностический аппарат с цифровой обработкой информации',1000,100,'VET'),
       ('Рентнтгенодиагностический комплекс с полным набором штативов (1-е, 2-е, 3-е рабочие места',1000,100,'VET'),
       ('Рентгеновский аппарат для рентгеноскопии (1-е рабочее место-поворотный стол-штатив ПСШ)',1000,100,'VET'),
       ('Рентгеновский аппарат для рентгенографии (2-е и 3-е рабочие места - стол снимков и стойка снимков', 1000,100,'VET'),
       ('Ангиографический комплекс',400,100,'VET'),
       ('Рентгеновский компьютерный томограф',400,125,'VET'),
       ('Хирургический передвижной аппарат с УРИ',200,100,'VET'),
       ('Палатный рентгеновский аппарат',200,90,'VET'),
       ('Рентгеноурологический стол',400,90,'VET'),
       ('Рентгеновский аппарат для литотрипсии',200,90,'VET'),
       ('Маммографический рентгеновский аппарат',200,40,'VET'),
       ('Рентгеновский аппарат для планирования лучевой терапии (симулятор)',200,100,'VET'),
       ('Аппарат для близкодистанционной рентгенотерапии',5000,100,'VET'),
       ('Аппарат для дальнедистанционной рентгенотерапии',12000,250,'VET'),
       ('Остеоденситометр для всего тела',200,0,'VET'),
       ('Остеоденситометр для конечностей',100,70,'VET'),
       ('Остеоденситометр для всего тела и его частей с использованем широкого пучка излучения и двухмерного цифрового детектора',50,0,'VET')
;