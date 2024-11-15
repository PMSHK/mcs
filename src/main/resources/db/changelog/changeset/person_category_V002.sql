create table if not exists room_category_desc
(
    id bigint primary key generated always as identity unique ,
    value varchar (1024) not null
);
create table if not exists room_load_coefficient
(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    value numeric(3,2) not null
);
create table if not exists work_shift
(
    id bigint primary key generated always as identity unique,
    shift_coefficient numeric(2,1) not null,
    whole_time bigint not null
);
create table if not exists max_dose
(
    id bigint primary key generated always as identity unique,
    dose real not null
);
create table if not exists dmd
(
    id             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    value          numeric(3,1) not null
);
create table if not exists room_category
(
    id bigint primary key generated always as identity unique ,
    name varchar (1024) not null,
    description_id bigint not null ,
    CONSTRAINT fk_description_id FOREIGN KEY (description_id) REFERENCES room_category_desc(id)
);
create table if not exists dmd_param_storage
(
    id bigint primary key generated always as identity unique,
    room_category_id bigint not null ,
    dmd_id bigint not null ,
    max_dose_id bigint not null ,
    work_shift_id bigint not null ,
    room_load_coefficient_id bigint not null ,
    CONSTRAINT fk_room_category_id FOREIGN KEY (room_category_id) REFERENCES room_category(id),
    CONSTRAINT fk_dmd_id FOREIGN KEY (dmd_id) REFERENCES dmd(id),
    CONSTRAINT fk_max_dose_id FOREIGN KEY (max_dose_id) REFERENCES max_dose(id),
    CONSTRAINT fk_work_shift_id FOREIGN KEY (work_shift_id) REFERENCES work_shift(id),
    CONSTRAINT fk_room_load_coefficient_id FOREIGN KEY (room_load_coefficient_id) REFERENCES room_load_coefficient(id)
);

insert into room_category_desc(value)
values ('Помещения постоянного пребывания персонала группы А (процедурная, комната управления, комната приготовления бария, фотолаборатория, кабинет врача и др.)'),
       ('Помещения, смежные по вертикали и горизонтали с процедурной рентгеновского кабинета, имеющие постоянные рабочие места персонала группы Б'),
       ('Помещения, смежные по вертикали и горизонтали с процедурной рентгеновского кабинета без постоянных рабочих мест (холл, гардероб, лестничная площадка, коридор, комната отдыха, уборная, кладовая и др.)'),
        ('Помещения эпизодического пребывания персонала группы Б (технический этаж, подвал, чердак и др.)'),
        ('Палаты стационара, смежные по вертикали и горизонтали с процедурной рентгеновского кабинета'),
        ('Территория, прилегающая к наружным стенам процедурной рентгеновского кабинета'),
        ('Жилые помещения, смежные с процедурной рентгеностоматологического кабинета')
;
insert into dmd (value)
values (0.2),
       (0.3),
       (0.8),
       (1.3),
       (1.8),
       (2.5),
       (2.8),
       (6.5),
       (10),
       (13),
       (40)
;

insert into room_category (name,description_id)
values ('Персонал группы А',1),
       ('Персонал группы А с комнатой управления смежной с 2-я процедурными',1),
       ('Персонал группы Б',2),
       ('Помещение временного пребывания',3),
       ('Помещения без постоянных рабочих мест',3),
       ('Население, кладовые и др.',3),
       ('Технические помещения',4),
       ('Палата',5),
       ('Территория',6),
       ('Жилое помещение',7),
       ('Палата с учетом 3-х смен',5),
       ('Территория с учетом 3-х смен',6),
       ('Жилое помещение с учетом 3-х смен',7)
;

insert into max_dose (dose)
values (1),
       (5),
       (20)
;

insert into work_shift (shift_coefficient,whole_time)
values (1,1500),
       (1.3,2000),
       (2,3000),
       (3,3000)
;

insert into room_load_coefficient (value)
values (0.06),
       (0.12),
       (0.25),
       (1)
;

insert into dmd_param_storage(room_category_id,dmd_id,max_dose_id,work_shift_id,room_load_coefficient_id)
values (1,10,3,1,4),
       (2,8,3,1,4),
       (3,6,2,2,4),
       (4,9,2,2,3),
       (5,9,2,2,3),
       (6,9,2,2,3),
       (7,11,2,2,1),
       (8,4,1,3,3),
       (9,7,1,3,2),
       (10,2,1,3,4),
       (11,3,1,4,3),
       (12,5,1,4,2),
       (13,1,1,4,4)
;