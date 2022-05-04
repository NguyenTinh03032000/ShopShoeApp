use shop_shoe;
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_SALESMAN');

INSERT INTO users(id,name,address,phone_number,username,email,password,scores,createDate)
VALUES(1,'Nguyễn Văn Tính','An Nhơn, Bình Định',0387640548,'Tinh','Tinh@gmail.com','$2a$12$P04OuBDEGusXOZ3Gfxfjnu/zREtM0WBe2LlI2QvB8oOFW0xQKfoJS',0,'2022-04-30 16:04:00');
INSERT INTO users(id,name,address,phone_number,username,email,password,scores,createDate)
VALUES(2,'Dương Tiến Lĩnh','Phù Cát, Bình Định',0387625947,'Linh','Linh@gmail.com','$2a$12$P04OuBDEGusXOZ3Gfxfjnu/zREtM0WBe2LlI2QvB8oOFW0xQKfoJS',0,'2022-04-30 16:04:00');
INSERT INTO users(id,name,address,phone_number,username,email,password,scores,createDate)
VALUES(3,'Tô Văn Tiên','Phú Yên',0354687256,'Tien','Tien@gmail.com','$2a$12$P04OuBDEGusXOZ3Gfxfjnu/zREtM0WBe2LlI2QvB8oOFW0xQKfoJS',0,'2022-04-30 16:04:00');
INSERT INTO user_roles(user_id,role_id) VALUES(1,2);
INSERT INTO user_roles(user_id,role_id) VALUES(1,1);
INSERT INTO user_roles(user_id,role_id) VALUES(1,3);
INSERT INTO user_roles(user_id,role_id) VALUES(2,1);
INSERT INTO user_roles(user_id,role_id) VALUES(3,3);

INSERT INTO category(id,name,create_Date,update_Date) VALUES(1,'Jordan','2022-04-30 16:04:00','2022-04-30 16:04:00');
INSERT INTO category(id,name,create_Date,update_Date) VALUES(2,'Football','2022-04-30 16:04:00','2022-04-30 16:04:00');
INSERT INTO category(id,name,create_Date,update_Date) VALUES(3,'Golf','2022-04-30 16:04:00','2022-04-30 16:04:00');

INSERT INTO product(id,name,price,description,id_category,image,create_Date,update_Date)
VALUES(1,'Air Jordan XXXVI PF',5439000,'The Air Jordan XXXVI is not just the next shoe up in the iconic franchise; it is an expression of the drive and energy that sparked a basketball revolution.','1','','2022-04-30 16:04:00','2022-04-30 16:04:00');
INSERT INTO product(id,name,price,description,id_category,image,create_Date,update_Date)
VALUES(2,'Nike Tiempo Legend 9 Academy MG',2189000,'1 of our lightest Tiempos to date, the Nike Tiempo Legend 9 Academy MG lets you go on the offensive with a low-profile design that is reinvented for attackers.','2','','2022-04-30 16:04:00','2022-04-30 16:04:00');
INSERT INTO product(id,name,price,description,id_category,image,create_Date,update_Date)
VALUES(3,'Nike Air Zoom Infinity Tour NEXT%',4699000,'We listened to the feedback you had on the Infinity Tour, refined it and brought it to life on the Nike Air Zoom Infinity Tour NEXT%.','3','','2022-04-30 16:04:00','2022-04-30 16:04:00');

INSERT INTO cart(id,total_money,id_user) VALUES(1,5439000,1);
INSERT INTO cart_index(id,amount,id_cart,id_product) VALUES(1,1,1,1);

