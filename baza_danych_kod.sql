DROP TABLE IF EXISTS auth_user_role;
DROP TABLE IF EXISTS auth_role;
DROP TABLE IF EXISTS auth_user;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user_item;
DROP TABLE IF EXISTS arena;



CREATE TABLE auth_role (
  auth_role_id serial PRIMARY KEY,
  role_name varchar(255) DEFAULT NULL,
  role_desc varchar(255) DEFAULT NULL
);


INSERT INTO auth_role VALUES (1,'SUPER_USER','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2,'ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3,'SITE_USER','This user has access to site, after login - normal user');


CREATE TABLE auth_user (
  auth_user_id serial PRIMARY KEY,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  nick_name varchar(255) NOT NULL,
  lvl integer NOT NULL,  
  xp integer NOT NULL,
  money integer NOT NULL,
  strength integer NOT NULL,
  dexterity integer NOT NULL,  
  intelligence integer NOT NULL,
  health integer NOT NULL,
  health_points integer NOT NULL,
  current_health integer NOT NULL,  
  armor integer NOT NULL,
  DMG integer NOT NULL,  
  status varchar(255),
  challange integer
);


CREATE TABLE auth_user_role (
  auth_user_id integer NOT NULL,
  auth_role_id integer NOT NULL,
  PRIMARY KEY (auth_user_id,auth_role_id),
  CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
  CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id)
);


insert into auth_user (auth_user_id,first_name,last_name,nick_name,email,password,status,lvl,xp,money,strength,dexterity,intelligence,health,health_points,current_health,armor,DMG,challange) values (0,'Karol','Pawlukowicz','FekeN','siema@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED',1,0,100,5,5,5,5,100,100,20,5,1);

insert into auth_user (auth_user_id,first_name,last_name,nick_name,email,password,status,lvl,xp,money,strength,dexterity,intelligence,health,health_points,current_health,armor,DMG,challange) values (1,'Karol2','Pawlukowicz2','FekeN2','siema2@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED',2,3,100,8,8,8,8,188,188,20,5,1);

insert into auth_user (auth_user_id,first_name,last_name,nick_name,email,password,status,lvl,xp,money,strength,dexterity,intelligence,health,health_points,current_health,armor,DMG,challange) values (2,'Karol3','Pawlukowicz3','FekeN3','siema3@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED',1,0,100,5,5,5,5,100,100,20,5,2);

insert into auth_user (auth_user_id,first_name,last_name,nick_name,email,password,status,lvl,xp,money,strength,dexterity,intelligence,health,health_points,current_health,armor,DMG,challange) values (3,'Karol4','Pawlukowicz4','FekeN4','siema4@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED',1,0,100,5,5,5,5,100,100,20,5,2);


insert into auth_user_role (auth_user_id, auth_role_id) values ('0','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('0','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('0','3');

insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','3');

insert into auth_user_role (auth_user_id, auth_role_id) values ('2','3');

insert into auth_user_role (auth_user_id, auth_role_id) values ('3','3');




CREATE TABLE item (
  item_id serial PRIMARY KEY,
  item_name varchar(255) NOT NULL,
  item_required_level integer NOT NULL,
  item_attack_demage integer NOT NULL,
  item_armor integer NOT NULL,
  item_type integer NOT NULL
);


CREATE TABLE user_item (
  auth_user_id integer NOT NULL,
  item_id integer NOT NULL,
  PRIMARY KEY (auth_user_id,item_id),
  CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
  CONSTRAINT FK_user_item FOREIGN KEY (item_id) REFERENCES item (item_id)
);

insert into item (item_id,item_name,item_required_level,item_attack_demage,item_armor,item_type) values (1,'Miecz',1,7,0,1);
insert into item (item_id,item_name,item_required_level,item_attack_demage,item_armor,item_type) values (2,'Zbroja',1,0,10,2);


insert into user_item (auth_user_id, item_id) values ('2','1');
insert into user_item ( auth_user_id, item_id) values ('2','2');




CREATE TABLE arena (
  arena_id serial PRIMARY KEY,
  user_id_to integer NOT NULL,
  user_id_from integer NOT NULL
);






