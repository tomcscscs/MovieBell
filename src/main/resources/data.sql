insert into accounts(nickname, user_password, username)
	 values 
	 ('마스터킴','{bcrypt}$2a$12$ITKrfItDZeabpDdYxpkO0OTr/kELv2ImuLRWUibJhiRiRtvF2bV3W','tom88'),
	 ('부조장','{bcrypt}$2a$12$ITKrfItDZeabpDdYxpkO0OTr/kELv2ImuLRWUibJhiRiRtvF2bV3W','brown88');
	 
	 
insert into posts(user_id,title,contents, write_at) values ('tom88','이거봐라','안녕하세요 이게 본문이죠. 날씨가 춥죠 옷 잘 챙겨입으세요.','2024-01-22 12:30:00'),
('brown88','지금은 그렇다.','여기 아무도 없냐? 왜이렇게 분위기가 쳐진거야?','2024-01-20 09:30');