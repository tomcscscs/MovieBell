insert into accounts(nickname, user_password, username)
	 values 
	 ('마스터킴','{bcrypt}$2a$12$ITKrfItDZeabpDdYxpkO0OTr/kELv2ImuLRWUibJhiRiRtvF2bV3W','tom88'),
	 ('부조장','{bcrypt}$2a$12$ITKrfItDZeabpDdYxpkO0OTr/kELv2ImuLRWUibJhiRiRtvF2bV3W','brown88');
	 
	 
insert into posts(user_id, contents, write_at) values ('tom88','안녕하세요','2024-01-22 12:30:00'),
('brown88','여기 아무도 없냐?','2024-01-20 09:30');