﻿create database MusicStreaming
use MusicStreaming

CREATE TABLE IMAGES (
	ACCESSID  NVARCHAR(100) PRIMARY KEY,
	URL VARCHAR(MAX),
	PUBLICID VARCHAR(MAX),
	HEIGHT INT,
	WEIGHT INT
);
CREATE TABLE ROLE (
	IDROLE INT IDENTITY(1,1) PRIMARY KEY,
	NAMEROLE VARCHAR(35)
);

CREATE TABLE ACCOUNTS (
	EMAIL VARCHAR(255) PRIMARY KEY,
	PASSWORD VARCHAR(500),
	USENAME NVARCHAR(55),
	BIRTHDAY DATE,
	GENDER INT,
	COUNTRY NVARCHAR(55),
	ISVERIFY BIT,
	VERIFYCATIONCODE VARCHAR(250),
	VERIFYCATIONCODEEXPIRES DATETIME,
	REMAININGVERIFYCATION INT DEFAULT 3,
	ISBLOCKED BIT DEFAULT 0,
	REFRESHTOKEN VARCHAR(max),
	IMAGEID NVARCHAR(100),
	
	FOREIGN KEY (IMAGEID) REFERENCES IMAGES(ACCESSID) ON DELETE SET NULL ON UPDATE CASCADE,
);

CREATE TABLE AUTHOR (
	AUTHORID BIGINT IDENTITY(1,1) PRIMARY KEY,
	IDROLE INT,
	EMAIL VARCHAR(255),

	FOREIGN KEY (IDROLE) REFERENCES ROLE(IDROLE) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (EMAIL) REFERENCES ACCOUNTS(EMAIL) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE FOLLOWER (
	FOLLOWERID INT IDENTITY(1,1) PRIMARY KEY,
	FOLLOWDATE DATE,
	ACCOUNT_A BIGINT,
	ACCOUNT_B BIGINT,

	FOREIGN KEY (ACCOUNT_A) REFERENCES AUTHOR(AUTHORID),
	FOREIGN KEY (ACCOUNT_B) REFERENCES AUTHOR(AUTHORID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE SUBCRIPTIONS (
	SUBCRIPTIONID BIGINT IDENTITY(1,1) PRIMARY KEY,
	SUBCRIPTIONTYPE VARCHAR(100),
	PRICE FLOAT,
	PRDSTRIPEID VARCHAR(500),
	PRDPAYPALID VARCHAR(500),
	PLAYLISTALLOW INT,/*quantity playlist*/
	NIP INT, /*Number in playlist*/
	DESCRIPTION NVARCHAR(500),
	CREATEDATE DATE,
	DURATION INT,
);

CREATE TABLE USERTYPE (
	USERTYPEID BIGINT IDENTITY(1,1) PRIMARY KEY,
	NAMETYPE VARCHAR(20),
	STARTDATE DATE,
	ENDDATE DATE,
	STATUS NVARCHAR(55),
	PAYMENTSTATUS INT,
	ACCOUNTID VARCHAR(255),
	SUBCRIPTIONID BIGINT,

	FOREIGN KEY (ACCOUNTID) REFERENCES ACCOUNTS(EMAIL) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (SUBCRIPTIONID) REFERENCES SUBCRIPTIONS(SUBCRIPTIONID) ON DELETE CASCADE ON UPDATE CASCADE,

);

CREATE TABLE ARTIST (
	ARTISTID BIGINT IDENTITY(1,1) PRIMARY KEY,
	ARTISTNAME NVARCHAR(55),
	DATEOFBIRTH DATE,
	FULLNAME NVARCHAR(55),
	PLACEOFBIRTH NVARCHAR(55),
	BIO NVARCHAR(max),
	IMAGEGALLERY VARCHAR(500),
	PUBLICIDIMAGEGALLERY VARCHAR(MAX),
	SOCIALMEDIALINKS VARCHAR(255),
	ACTIVE NVARCHAR(55),
	DATESTARTED DATE,
	VERIFY BIT,
	PROFILEIMAGE NVARCHAR(100),
	BACKGROUDIMAGE NVARCHAR(100),
	EMAIL VARCHAR(255),

	FOREIGN KEY (EMAIL) REFERENCES ACCOUNTS(EMAIL) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (PROFILEIMAGE) REFERENCES IMAGES(ACCESSID) ON DELETE  NO ACTION,
	FOREIGN KEY (BACKGROUDIMAGE) REFERENCES IMAGES(ACCESSID) ON DELETE  NO ACTION,
);

CREATE TABLE SONGS (
	SONGSID BIGINT IDENTITY(1,1) PRIMARY KEY,
	SONGNAME NVARCHAR(55),
	IMAGEID NVARCHAR(100),
	REALEASEDAY DATETIME,
	ISDELETED BIT,
	DESCRIPTIONS NVARCHAR(max),
	ARTISTCREATE BIGINT,
	FOREIGN KEY (IMAGEID) REFERENCES IMAGES(ACCESSID) ON DELETE NO ACTION,
);

CREATE TABLE WRITTER (
	WRITTERID BIGINT IDENTITY(1,1) PRIMARY KEY,
	ARTISTID BIGINT,
	SONGSID BIGINT,

	FOREIGN KEY (ARTISTID) REFERENCES ARTIST(ARTISTID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (SONGSID) REFERENCES SONGS(SONGSID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE GENRE (
	ID INT IDENTITY(1,1) PRIMARY KEY,
	NAMEGENRE VARCHAR(30)
);

CREATE TABLE RECORDING (
	RECORDINGID BIGINT IDENTITY(1,1) PRIMARY KEY,
	RECORDINGIDNAME NVARCHAR(55),
	AUDIOFILEURL VARCHAR(MAX),
	PUBLICIDAUDIOFILE VARCHAR(MAX),
	LYRICSURL NVARCHAR(MAX),
	PUBLICIDLYRICS VARCHAR(MAX),
	LISTENED BIGINT,
	SONGSTYLE NVARCHAR(55),
	MOOD NVARCHAR(55),
	CULTURE NVARCHAR(55),
	INSTRUMENT NVARCHAR(255),
	VERSIONS NVARCHAR(55),
	STUDIO NVARCHAR(55),
	IDMV VARCHAR(200),
	PRODUCE NVARCHAR(55),
	RECORDINGDATE DATE,
	ISDELETED BIT,
	SONGSID BIGINT,
	EMAILCREATE VARCHAR(255),
	FOREIGN KEY (SONGSID) REFERENCES SONGS(SONGSID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE MONITOR(
	INSTRUMENTID BIGINT IDENTITY(1,1) PRIMARY KEY,
	ACCOUNT VARCHAR(255),
	RECORDINGID BIGINT,

	FOREIGN KEY (ACCOUNT) REFERENCES ACCOUNTS(EMAIL) ON DELETE SET DEFAULT ON UPDATE CASCADE,
	FOREIGN KEY (RECORDINGID) REFERENCES RECORDING(RECORDINGID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE SONGGENRE(
	ID BIGINT IDENTITY(1,1) PRIMARY KEY,
	IDGENRE INT,
	IDRECORD BIGINT,

	FOREIGN KEY (IDGENRE) REFERENCES GENRE(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (IDRECORD) REFERENCES RECORDING(RECORDINGID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE WISHLIST (
	WISHLISTID INT IDENTITY(1,1) PRIMARY KEY,
	ADDDATE DATE,
	USERTYPEID BIGINT,
	RECORDINGID BIGINT,

	FOREIGN KEY (RECORDINGID) REFERENCES RECORDING(RECORDINGID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(USERTYPEID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE ADVERTISEMENT (
	IDADS BIGINT IDENTITY(1,1) PRIMARY KEY,
	TITLE NVARCHAR(255),
	CONTENT NVARCHAR(255),
	URL NVARCHAR(2000),
	STARTDATE DATE,
	ENDDATE DATE,
	BUDGET FLOAT,
	STATUS NVARCHAR(55),
	TARGETAUDIENCE NVARCHAR(55),
	CLICKED BIGINT,
	TAG VARCHAR(55),
	PRIORITY INT,
	BANNER NVARCHAR(100),
	ACCOUNTID VARCHAR(255),
	AUDIOFILE NVARCHAR(2000),
	PUBLICIDAUDIO NVARCHAR(2000),
	FOREIGN KEY (ACCOUNTID) REFERENCES ACCOUNTS(EMAIL) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (BANNER) REFERENCES IMAGES(ACCESSID) ON DELETE NO ACTION,
	
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,
);

CREATE TABLE NEWS (
	IDNEWS BIGINT IDENTITY(1,1) PRIMARY KEY,
	TITLE NVARCHAR(255),
	CONTENT NVARCHAR(255),
	IMAGE NVARCHAR(100),
	PUBLISHDATE DATE,
	LASTMODIFIED DATETIME,
	AUTHORID VARCHAR(255),
	CREATEFOR VARCHAR(20),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,

	FOREIGN KEY (AUTHORID) REFERENCES ACCOUNTS(EMAIL) ON DELETE SET DEFAULT ON UPDATE CASCADE,
	FOREIGN KEY (IMAGE) REFERENCES IMAGES(ACCESSID) ON DELETE NO ACTION,
);

CREATE TABLE TAGS (
	TAGID BIGINT IDENTITY(1,1) PRIMARY KEY,
	NAMETAG NVARCHAR(55),

	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,
);

CREATE TABLE PODCAST (
	PODCASTID BIGINT IDENTITY(1,1) PRIMARY KEY,
	PODCASTNAME NVARCHAR(55),
	BIO NVARCHAR(max),
	SOCIALMEDIALINK VARCHAR(1000),
	RELEASEDATE DATE,
	AUTHORNAME NVARCHAR(100),
	LANGUAGE NVARCHAR(55),
	IMGAGEID NVARCHAR(100),
	CATEGORY BIGINT,
	ACCOUNTID VARCHAR(255),
	RATE BIGINT,

	FOREIGN KEY (ACCOUNTID) REFERENCES ACCOUNTS(EMAIL) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (IMGAGEID) REFERENCES IMAGES(ACCESSID)ON DELETE NO ACTION ,	
	FOREIGN KEY (CATEGORY) REFERENCES TAGS(TAGID) ON DELETE NO ACTION ON UPDATE CASCADE,
);


CREATE TABLE EPISODES (
	EPISODESID BIGINT IDENTITY(1,1) PRIMARY KEY,
	FILEURL NVARCHAR(MAX),
	PUBLICIDFILE NVARCHAR(MAX),
	EPISODESTITLE NVARCHAR(1000),
	DESCRIPTIONS NVARCHAR(1000),
	PUBLISHDATE DATETIME,
	SESSIONNUMBER INT,
	EPNUMBER INT,
	EPTYPE NVARCHAR(55),
	CONTENT NVARCHAR(55),
	ISPUBLIC BIT,
	ISDELETED BIT,
	PODCASTID BIGINT,
	IMAGEEP NVARCHAR(100),
	LIKES BIGINT,
	FOREIGN KEY (IMAGEEP) REFERENCES IMAGES(ACCESSID) ON DELETE NO ACTION,
	FOREIGN KEY (PODCASTID) REFERENCES PODCAST(PODCASTID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE REPORT (
	REPORTID INT IDENTITY(1,1) PRIMARY KEY,
	USERTYPEID BIGINT,
	REPORTDATE DATE,
	DESCRIPTION NVARCHAR(55),
	ARTISTID BIGINT,
	RECORDINGID BIGINT,
	POSTCASTID BIGINT,
	EPISODESID BIGINT,

	FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(USERTYPEID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (EPISODESID) REFERENCES EPISODES(EPISODESID) ON DELETE NO ACTION,
	FOREIGN KEY (POSTCASTID) REFERENCES PODCAST(PODCASTID)  ON DELETE NO ACTION,
	FOREIGN KEY (RECORDINGID) REFERENCES RECORDING(RECORDINGID)  ON DELETE NO ACTION,
	FOREIGN KEY (ARTISTID) REFERENCES ARTIST(ARTISTID)  ON DELETE NO ACTION,
	
);

CREATE TABLE PLAYLISTS (
	PLAYLISTID BIGINT IDENTITY(1,1) PRIMARY KEY,
	PLAYLISTNAME NVARCHAR(55),
	QUANTITY INT,
	ISPUBLIC BIT,
	STATUS VARCHAR(55),
	CREATDATE DATE,
	USERTYPEID BIGINT,
	IMAGE NVARCHAR(100),

	FOREIGN KEY (IMAGE) REFERENCES IMAGES(ACCESSID) ON DELETE NO ACTION,
	FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(USERTYPEID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE PLAYLIST_PODCAST (
	PLAYLIST_PODCASTID BIGINT IDENTITY(1,1) PRIMARY KEY,
	PLAYLISTID BIGINT,
	EPISODESID BIGINT,

	FOREIGN KEY (PLAYLISTID) REFERENCES PLAYLISTS(PLAYLISTID) ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (EPISODESID) REFERENCES EPISODES(EPISODESID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE PLAYLIST_RECORDING (
	PLAYLIST_RECORDINGID BIGINT IDENTITY(1,1) PRIMARY KEY,
	RECORDINGID BIGINT,
	PLAYLISTSID BIGINT,

	FOREIGN KEY (RECORDINGID) REFERENCES RECORDING(RECORDINGID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (PLAYLISTSID) REFERENCES PLAYLISTS(PLAYLISTID) ON DELETE CASCADE ON UPDATE CASCADE,
);


CREATE TABLE ALBUM (
	ALBUMID BIGINT IDENTITY(1,1) PRIMARY KEY,
	ALBUMNAME NVARCHAR(55),
	RELEASEDATE DATETIME,
	COVERIMAGE NVARCHAR(100),
	ARTISTID BIGINT,
	DESCRIPTIONS NVARCHAR(max),
	FOREIGN KEY (COVERIMAGE) REFERENCES IMAGES(ACCESSID) ON DELETE NO ACTION,
	FOREIGN KEY (ARTISTID) REFERENCES ARTIST(ARTISTID) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE TRACK (
	TRACKID BIGINT IDENTITY(1,1) PRIMARY KEY,
	ALBUMID BIGINT,
	RECORDINGID BIGINT,
	TRACKNUMBER INT,

	FOREIGN KEY (RECORDINGID) REFERENCES RECORDING(RECORDINGID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (ALBUMID) REFERENCES ALBUM(ALBUMID)ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE ACCESS (
	ACCESSID INT IDENTITY(1,1) PRIMARY KEY,
	PLAYLIST_RECORDINGID BIGINT,
	USERTYPEID BIGINT,
	EPISODESID BIGINT,

	FOREIGN KEY (EPISODESID) REFERENCES PLAYLIST_PODCAST(PLAYLIST_PODCASTID),
	FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(USERTYPEID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (PLAYLIST_RECORDINGID) REFERENCES PLAYLIST_RECORDING(PLAYLIST_RECORDINGID)
);

CREATE TABLE SONG_STYLE (
	SONG_STYLEID INT IDENTITY(1,1) PRIMARY KEY,
	SONG_STYLENAME NVARCHAR(55),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,
);

CREATE TABLE MOOD (
	MOODID INT IDENTITY(1,1) PRIMARY KEY,
	MOODNAME NVARCHAR(55),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME
);

CREATE TABLE INSTRUMENT (
	INSTRUMENTID INT IDENTITY(1,1) PRIMARY KEY,
	INSTRUMENTNAME NVARCHAR(55),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME
);

CREATE TABLE CULTURE (
	CULTUREID INT IDENTITY(1,1) PRIMARY KEY,
	CULTURENAME NVARCHAR(55),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,
);


CREATE TABLE NOTIFICATION (
	NOTIFICATIONID INT IDENTITY(1,1) PRIMARY KEY,
	TITILE NVARCHAR(50),
	CONTENT NVARCHAR(250),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME
);

CREATE TABLE SLIDE (
	SLIDEID INT IDENTITY(1,1) PRIMARY KEY,
	POSITION NVARCHAR(55),
	LISTIMAGE VARCHAR(55),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,
);

CREATE TABLE COUNTRY (
	ID VARCHAR(15) PRIMARY KEY,
	NAMECOUNTRY VARCHAR(30),
	CREATEBY NVARCHAR(255),
	CREATEDATE DATETIME,
	MODIFIEDBY NVARCHAR(255),
	MODIFIDATE DATETIME,
);

INSERT INTO  IMAGES 
	VALUES ('Avatar/System/807831_rrsd2v.png','https://res.cloudinary.com/div9ldpou/image/upload/v1696293833/Avatar/System/807831_rrsd2v.png','https://res.cloudinary.com/div9ldpou/image/upload/v1696293833/Avatar/System/807831_rrsd2v.png',512,512)


INSERT INTO ACCOUNTS(EMAIL,PASSWORD,USENAME,BIRTHDAY,GENDER,COUNTRY,ISVERIFY,IMAGEID) 
	VALUES ('mck@gmail.com','$2a$12$eX7AUZVUW.QC.6ZNxwXtUu0Qn03546/D58VH/oqnN3uhXF1044v.G','rpt.mckeyyyyy','2001/03/02',1,'VN',1,'Avatar/System/807831_rrsd2v.png')
	
INSERT INTO ACCOUNTS(EMAIL,PASSWORD,USENAME,BIRTHDAY,GENDER,COUNTRY,ISVERIFY,IMAGEID) 
	VALUES ('jvke@gmail.com','$2a$12$eX7AUZVUW.QC.6ZNxwXtUu0Qn03546/D58VH/oqnN3uhXF1044v.G','jvkeeee','2001/03/03',1,'US',1,'Avatar/System/807831_rrsd2v.png')

INSERT INTO ACCOUNTS(EMAIL,PASSWORD,USENAME,BIRTHDAY,GENDER,COUNTRY,ISVERIFY,IMAGEID) 
	VALUES ('nhien@gmail.com','$2a$12$eX7AUZVUW.QC.6ZNxwXtUu0Qn03546/D58VH/oqnN3uhXF1044v.G','nhien','2001/03/03',1,'US',1,'Avatar/System/807831_rrsd2v.png')

Truncate table ARTIST
INSERT INTO ARTIST(ARTISTNAME,FULLNAME,DATEOFBIRTH,PLACEOFBIRTH,BIO,SOCIALMEDIALINKS,VERIFY,EMAIL) 
			VALUES('MCK','Nghiêm Vũ Hoàng Long','2001/03/02', N'Hà Nội, Việt Nam','Hanoi born-and-raised. CDSL // RPT
Nghiêm Vũ Hoàng Long, also known as MCK, is a rapper/singer-songwriter from Hanoi, Vietnam. His music career started in early 2018 as an independent singer/songwriter under the alias Ngơ. His debut single was the smashing hit "Tình Đắng Như Ly Cà Phê" featuring Nân, accumulating over 60 million streams across the DSPs since its upload, kick-starting one of the most successful debuts in the local independent scene.
MCKs career took a turn in 2020 when he appeared as a contestant on the hit TV show "Rap Việt.. He has proven himself to be a force to be reckoned with in Vietnams hip-hop scene as one of the most prominent independent rappers. MCK further solidified his position in the industry by winning the WeChoice Awards for Most Promising Hip-hop Act in 2020.
In recent years, he has become a household name and an unstoppable force on the local chart, especially with the release of his debut album "99%." The album was immediately received with critical acclaim by the media and fans alike, with the single "Chìm Sâu" being the most streamed song on Spotify Việt Nam in 2022, and the rest of the album dominating Billboard Vietnams charts with 5 tracks in the top 10','https://www.instagram.com/rpt.mckeyyyyy/',1,'mck@gmail.com')



INSERT INTO SLIDE VALUES('ARTIST','http://res.cloudinary.com/div9ldpou/image/upload/v1697612547/ImageManager/Image%20News/admin-Dashboard.png.png')

INSERT INTO [ROLE] VALUES('STAFF'),('MANAGER'),('ARTIST'),('USER'),('PODCAST')

INSERT INTO AUTHOR VALUES(4,'jvke@gmail.com')
INSERT INTO AUTHOR VALUES(3,'mck@gmail.com')
INSERT INTO AUTHOR VALUES(4,'nhien@gmail.com')

INSERT INTO SUBCRIPTIONS VALUES(1,123.123,'BASIC usage package', '2023-10-21',1)
INSERT INTO SUBCRIPTIONS VALUES(2,678.678,'PREMIUM usage package', '2023-10-21',1)

INSERT INTO USERTYPE  VALUES ('BASIC','2023-10-21','2024-10-21','OK',1,'jvke@gmail.com',1),
							 ('PREMIUM','2023-10-21','2024-10-21','OK',1,'jvke@gmail.com',1)
INSERT INTO USERTYPE  VALUES ('BASIC','2023-10-21','2024-10-21','OK',2,'mck@gmail.com',1)
INSERT INTO USERTYPE  VALUES ('BASIC','2023-10-21','2024-10-21','OK',3,'nhien@gmail.com',1)
							
							


