# 데이터베이스 생성

DROP DATABASE IF EXISTS untactTeacher;

CREATE DATABASE untactTeacher;

USE untactTeacher;

 

# 요청사항 테이블 생성

CREATE TABLE `order` (

    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    regDate DATETIME NOT NULL,

    updateDate DATETIME NOT NULL,    

    title CHAR(200) NOT NULL,

    term CHAR(50) NOT NULL,    #기간

    `body` TEXT NOT NULL,       #추가요청

    funeralHome CHAR(200) NOT NULL, #장례식장 위치

    `directorId` INT(10) UNSIGNED NOT NULL, #전문가 ID

    `clientId` INT(10) UNSIGNED NOT NULL, #고객ID
    
    `helperName` TEXT DEFAULT '' NOT NULL ,
    
    stepLevel SMALLINT(2) UNSIGNED DEFAULT 1 NOT NULL COMMENT '(1=의뢰요청(의뢰검토),2=의뢰승인(장례준비),3=장례진행중,4=장례종료(결제미완료),5=결제완료)'

)ENGINE=INNODB DEFAULT CHARSET=utf8;

# 테스트 의뢰 생성
INSERT INTO `order`
SET regDate = NOW(),

    updateDate = NOW(),

    title = 'user2님 의뢰',

    term = '2021.03.22 ~ 2021.03.25',

    funeralHome = '대전장례식장',

    `body` = '기타 요청 사항',

    `directorId` = 1,

    `clientId` = 2;

 

INSERT INTO `order`
SET regDate = NOW(),

    updateDate = NOW(),

    title = 'user3님 의뢰',  

    term = '2021.03.22 ~ 2021.03.25',

    funeralHome = '익산장례식장',

    `body` = '기타 요청 사항2',

    `directorId` = 1,

    `clientId` = 3;

 



CREATE TABLE `expert` (

    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    regDate DATETIME NOT NULL,

    updateDate DATETIME NOT NULL,

    loginId CHAR(30) NOT NULL,

    loginPw VARCHAR(100) NOT NULL,
    
    authKey CHAR(80) NOT NULL UNIQUE,

    relId SMALLINT(2) UNSIGNED DEFAULT 1 NOT NULL COMMENT '(1=지도사,3=도우미)',     

    acknowledgment_stap SMALLINT(2) UNSIGNED DEFAULT 1 NOT NULL COMMENT '(1=가입대기 2=가입승인 3=가입실패)',

    `name` CHAR(30) NOT NULL,

    `nickname` CHAR(30) NOT NULL,

    `email` CHAR(100) NOT NULL,

    `cellphoneNo` CHAR(20) NOT NULL,

    `sido` CHAR(100) NOT NULL,  #활동지역

    `license` CHAR(100) NOT NULL,   #자격증    

    `career` CHAR(100) NOT NULL #경력

)ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `expert`

SET regDate = NOW(),

updateDate = NOW(),

loginId = "asd",

loginPw = "asd",

relId = "1",

acknowledgment_stap = "2",

`name` = "asd",

nickname = "asd",

cellphoneNo = "01012341234",

email = "asdf@asdf",

sido = "대전",

license = "장례지도사2급",

career = "2015.02~2021.03",
authKey = 'authKey1__1';

# 게시물 테이블 생성

CREATE TABLE article (

    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    regDate DATETIME NOT NULL,

    updateDate DATETIME NOT NULL,

    title CHAR(100) NOT NULL,

    place TEXT NOT NULL,

    expertId INT(10) NOT NULL

)ENGINE=INNODB DEFAULT CHARSET=utf8;

 CREATE TABLE `client` ( 
     id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT, 
     regDate DATETIME NOT NULL, 
     updateDate DATETIME NOT NULL, 
     loginId CHAR(30) NOT NULL, 
     loginPw VARCHAR(100) NOT NULL, 
     authKey CHAR(80) NOT NULL, 
     authLevel SMALLINT(2) UNSIGNED DEFAULT 3 NOT NULL COMMENT '(3= 의뢰인, 5=관리자)', 
     `name` CHAR(30) NOT NULL, 
     `nickname` CHAR(30) NOT NULL, 
     `email` CHAR(100) NOT NULL, 
     `cellphoneNo` CHAR(20) NOT NULL, 
     `address` CHAR(100) NOT NULL 
 )ENGINE=INNODB DEFAULT CHARSET=utf8; 


# 로그인 ID로 검색했을 때 
 ALTER TABLE `client` ADD UNIQUE INDEX (`loginId`); 
 
 
 # authKey 칼럼에 유니크 인덱스 추가 
 ALTER TABLE `client` ADD UNIQUE INDEX (`authKey`); 
 
 
 # 회원, 테스트 데이터 생성 
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'user1', 
     loginPw = 'user1', 
     authKey = 'authKey1__1', 
     authLevel = 7, 
     `name` = 'user1', 
     `nickname` = 'user1', 
     `email` = 'user1@user1.com', 
     `cellphoneNo` = 01011111111, 
     `address` = '대전광역시'; 
 
 
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'user2', 
     loginPw = 'user2', 
     authKey = 'authKey1__2', 
     authLevel = 3, 
     `name` = 'user2', 
     `nickname` = 'user2', 
     `email` = 'user2@user2.com', 
     `cellphoneNo` = 01022222222, 
     `address` = '경기도'; 
 
 
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'user3', 
     loginPw = 'user3', 
     authKey = 'authKey1__3', 
     authLevel = 3, 
     `name` = 'user3', 
     `nickname` = 'user3', 
     `email` = 'user3@user3.com', 
     `cellphoneNo` = 01033333333, 
     `address` = '전라북도'; 
      
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'tester1', 
     loginPw = 'tester1', 
     authKey = 'authKey1__4', 
     authLevel = 5, 
     `name` = 'tester1', 
     `nickname` = 'tester1', 
     `email` = 'tester1@tester1.com', 
     `cellphoneNo` = 01044444444, 
     `address` = '대전광역시'; 
 
 
 
 
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'tester2', 
     loginPw = 'tester2', 
     authKey = 'authKey1__5', 
     authLevel = 5, 
     `name` = 'tester2', 
     `nickname` = 'tester2', 
     `email` = 'tester2@tester2.com', 
     `cellphoneNo` = 01055555555, 
     `address` = '경기도'; 
 
 
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'tester3', 
     loginPw = 'tester3', 
     authKey = 'authKey1__6', 
     authLevel = 5, 
     `name` = 'tester3', 
     `nickname` = 'tester3', 
     `email` = 'tester3@tester3.com', 
     `cellphoneNo` = 01066666666, 
     `address` = '전라북도'; 
      
 INSERT INTO `client` 
 SET regDate = NOW(), 
     updateDate = NOW(), 
     loginId = 'tester4', 
     loginPw = 'tester4', 
     authKey = 'authKey1__7', 
     authLevel = 5, 
     `name` = 'tester4', 
     `nickname` = 'tester4', 
     `email` = 'tester4@tester4.com', 
     `cellphoneNo` = 01044444444, 
     `address` = '대전광역시'; 
 
 
 


/*
INSERT INTO article
(regDate, updateDate, memberId, title, `body`)
SELECT NOW(), NOW(), FLOOR(RAND() * 2) + 1, CONCAT('제목_', FLOOR(RAND() * 1000) + 1), CONCAT('내용_', FLOOR(RAND() * 1000) + 1)
FROM article;
*/

 


#ALTER TABLE `member` ADD COLUMN authKey CHAR(80) NOT NULL AFTER loginPw;

 

/*# 기존 회원의 authKey 데이터 채우기
UPDATE `member`
SET authKey = 'authKey1__1'
WHERE id = 1;
 
UPDATE `member`
SET authKey = 'authKey1__2'
WHERE id = 2;
 
# authKey 칼럼에 유니크 인덱스 추가
ALTER TABLE `member` ADD UNIQUE INDEX (`authKey`);
 
*/
# 파일 테이블 추가

CREATE TABLE genFile (

  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, # 번호

  regDate DATETIME DEFAULT NULL, # 작성날짜

  updateDate DATETIME DEFAULT NULL, # 갱신날짜

  delDate DATETIME DEFAULT NULL, # 삭제날짜

  delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0, # 삭제상태(0:미삭제,1:삭제)

  relTypeCode CHAR(50) NOT NULL, # 관련 데이터 타입(article, member)

  relId INT(10) UNSIGNED NOT NULL, # 관련 데이터 번호

  originFileName VARCHAR(100) NOT NULL, # 업로드 당시의 파일이름

  fileExt CHAR(10) NOT NULL, # 확장자

  typeCode CHAR(20) NOT NULL, # 종류코드 (common)

  type2Code CHAR(20) NOT NULL, # 종류2코드 (attatchment)

  fileSize INT(10) UNSIGNED NOT NULL, # 파일의 사이즈

  fileExtTypeCode CHAR(10) NOT NULL, # 파일규격코드(img, video)

  fileExtType2Code CHAR(10) NOT NULL, # 파일규격2코드(jpg, mp4)

  fileNo SMALLINT(2) UNSIGNED NOT NULL, # 파일번호 (1)

  fileDir CHAR(20) NOT NULL, # 파일이 저장되는 폴더명

  PRIMARY KEY (id),

  KEY relId (relId,relTypeCode,typeCode,type2Code,fileNo)

)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE helper(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(30) NOT NULL,
	loginPw VARCHAR(100) NOT NULL,   
	authKey CHAR(80) NOT NULL UNIQUE,		
	`name` CHAR(30) NOT NULL,	
	`email` CHAR(100) NOT NULL,
	`cellphoneNo` CHAR(20) NOT NULL,
	`sido` CHAR(100) NOT NULL,  #활동지역	
	`career` CHAR(100) NOT NULL #경력
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE helperOrder(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,            
    `orderId` INT(10) UNSIGNED NOT NULL, #신청한 장례식 ID    
    `helperId` INT(10) UNSIGNED NOT NULL, #신청자(도우미)Id
    `name` CHAR(200) NOT NULL,
    `career` CHAR(200) NOT NULL,
    `sido` CHAR(200) NOT NULL,
    stepLevel SMALLINT(2) UNSIGNED DEFAULT 1 NOT NULL COMMENT '(1=의뢰요청,2=의뢰승인)'
)ENGINE=INNODB DEFAULT CHARSET=utf8;




INSERT INTO `helperOrder`
SET regDate = NOW(),

    updateDate = NOW(),

    career = '2년',       

    `orderId` = 1,
    
    helperId = 1,
    
    stepLevel = 1,
    `name` = '1도움',
    sido = '대전광역시';
    

INSERT INTO `helperOrder`
SET regDate = NOW(),

    updateDate = NOW(),

    career = '3년',     

    `orderId` = 1,
    
    helperId = 1,
    
    stepLevel = 1,
    `name` = '1도움',
    sido = '대전광역시';


INSERT INTO `helperOrder`
SET regDate = NOW(),

    updateDate = NOW(),

    career = '4년',    

    `orderId` = 2,
    
    helperId = 1,
    
    stepLevel = 1,
    `name` = '2도움',
    sido = '대전광역시';