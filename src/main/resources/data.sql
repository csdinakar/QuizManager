insert into USERS (ADMIN, USERNAME, NAME, PASSWORD,LOCKED) values (true,'admin','Admin','A+98FnMIylJGjl2jhHcUYQ==',false);
insert into USERS (ADMIN, USERNAME, NAME, PASSWORD,LOCKED) values (false,'user','User','A+98FnMIylJGjl2jhHcUYQ==',false);

insert into category(name) values ('SPRING BOOT');

insert into question(category_id,options,text) values (1,'{"1":{"text":"The JDBC module provides a JDBC-abstraction layer that removes the need to do tedious JDBC related coding.","correct":false},"2":{"text":"The ORM module provides integration layers for popular object-relational mapping APIs, including JPA, JDO, Hibernate, and iBatis.","correct":false},"3":{"text":"The Java Messaging Service JMS module contains features for producing and consuming messages.","correct":false},"4":{"text":"All of the above.","correct":true}}','Which of the statement is correct ?');
insert into question(category_id,options,text) values (1,'{"1":{"text":"This scopes a single bean definition to have any number of object instances.","correct":true},"2":{"text":"This scopes the bean definition to a single instance per HTTP Request.","correct":false},"3":{"text":"This scopes the bean definition to a single instance per HTTP Session.","correct":false},"4":{"text":"This scopes the bean definition to a single instance per HTTP Application/ Global session.","correct":false}}','What is prototype scope?');
