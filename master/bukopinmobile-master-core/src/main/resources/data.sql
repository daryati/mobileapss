INSERT INTO category (ID_CATEGORY, CATEGORY_NAME, IS_ACTIVE) VALUES
	(1, 'Pulsa & Data', true),
	(2, 'Listrik', true),
	(3, 'Kartu Kredit', true),
	(4, 'Telepon & TV Kabel', true),
	(5, 'E-money', true),
	(6, 'Asuransi', true);
	
INSERT INTO configuration (id,create_by,create_date,description,name,update_by,update_date,value) VALUES 
	(227,'tika','2019-12-09 15:12:17.563','base url for user module','USER_BASE_URL','tika','2019-12-09 15:12:17.563','http://10.2.62.163:8080/'),
	(228,'tika','2019-12-09 15:12:52.981','url for get user by username from user module','URL_FINDUSER_BY_USERNAME','tika','2019-12-09 15:12:52.981','bukopinmobile-user/account/findUserByUsername'),
	(229,'tika','2019-12-09 15:13:33.358','url API to bukopin XLink for inquiry FT','URL_BUKOPIN_XLINK_INQUIRYFT','tika','2019-12-09 15:13:33.359','http://10.2.62.35:8080/personal/fundTransfer/inquiryFT'),
	(230,'tika','2019-12-09 15:13:54.756','url API to bukopin XLink for posting FT','URL_BUKOPIN_XLINK_POSTINGFT','tika','2019-12-09 15:13:54.756','http://10.2.62.35:8080/personal/fundTransfer/postingFT'),
	(231,'tika','2019-12-09 15:14:19.987','url API to get user token by token','URL_USER_GETUSERTOKEN_BYTOKEN','tika','2019-12-09 15:14:19.987','/bukopinmobile-user/api/userToken/getByToken'),
	(243,'tika','2019-12-09 16:45:49.993','base url for user module','MASTERDATA_BASE_URL','tika','2019-12-09 16:45:49.993','http://10.2.62.163:8082/'),
	(244,'eka','2020-01-06 14:25:00.000','reset password iddle duration','RESET_PASSWORD_DURATION','eka','2020-01-06 14:25:00.000','5'),
	(245,'eka','2020-01-06 14:25:00.000','admin fee','OVERBOOK_ADMIN_FEE','eka','2020-01-06 14:25:00.000','0'),
	(246,'eka','2020-01-06 14:25:00.000','admin fee','FUND_TRANSFER_ADMIN_FEE','eka','2020-01-06 14:25:00.000','');