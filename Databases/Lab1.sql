CREATE DATABASE SocialMedia;
USE SocialMedia;

CREATE TABLE Users (
	uid INT PRIMARY KEY IDENTITY,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	dateOfBirth DATE,
	gender varchar(20) CHECK (gender IN ('male', 'female', 'none')),
	lastLogin DATETIME
	CONSTRAINT uq_users UNIQUE(username, email)
);

CREATE TABLE Friendships (
	uid1 INT,
	uid2 INT,
	status varchar(50),
	CONSTRAINT fk_user1 FOREIGN KEY (uid1) REFERENCES Users(uid) 
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_user2 FOREIGN KEY (uid2) REFERENCES Users(uid),
	CONSTRAINT pk_friendship PRIMARY KEY (uid1,uid2),
	CONSTRAINT bi_friendship CHECK (uid1 < uid2)
);

CREATE TABLE Groups (
	gid INT PRIMARY KEY IDENTITY,
	name varchar(50) NOT NULL,
	description varchar(255)
);

CREATE TABLE GroupMemberships (
	uid INT,
	gid INT,
	CONSTRAINT fk_user FOREIGN KEY (uid) REFERENCES Users(uid)
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_group FOREIGN KEY (gid) REFERENCES Groups(gid)
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pk_membership PRIMARY KEY (uid,gid)
);

CREATE TABLE Posts (
	pid INT PRIMARY KEY IDENTITY,
	authorId INT,
	createdOn DATE DEFAULT GETDATE(), 
	title varchar(20) NOT NULL,
	text varchar(255),
	image IMAGE,
	CONSTRAINT fk_author FOREIGN KEY (authorId) REFERENCES Users(uid)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Likes (
	uid INT,
	pid INT,
	CONSTRAINT fk_userLike FOREIGN KEY (uid) REFERENCES Users(uid)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_postLike FOREIGN KEY (pid) REFERENCES Posts(pid),
	CONSTRAINT pk_like PRIMARY KEY (uid,pid)
);

CREATE TABLE Comments (
	uid INT,
	pid INT,
	content varchar(255) NOT NULL,
	CONSTRAINT fk_userComment FOREIGN KEY (uid) REFERENCES Users(uid)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_postComment FOREIGN KEY (pid) REFERENCES Posts(pid),
	CONSTRAINT pk_comment PRIMARY KEY (uid,pid)
);

CREATE TABLE Tags (
	tid INT PRIMARY KEY IDENTITY,
	name varchar(50) NOT NULL,
	description varchar(255)
);

CREATE TABLE UserInterests (
	uid INT,
	tid INT,
	CONSTRAINT fk_userInterest FOREIGN KEY (uid) REFERENCES Users(uid)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_tagInterest FOREIGN KEY (tid) REFERENCES Tags(tid),
	CONSTRAINT pk_interest PRIMARY KEY (uid,tid)
);

CREATE TABLE PostTags (
	pid INT,
	tid INT,
	CONSTRAINT fk_post FOREIGN KEY (pid) REFERENCES Posts(pid)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_tag FOREIGN KEY (tid) REFERENCES Tags(tid),
	CONSTRAINT pk_postTag PRIMARY KEY (pid,tid)
);