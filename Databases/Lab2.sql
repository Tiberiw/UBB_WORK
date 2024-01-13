INSERT INTO Users (username, email, dateOfBirth, gender)
VALUES
	('Tony Stark', 'tonystark@gmail.com', '1970-10-22', 'male'),
	('Steve Rogers', 'steverogers@yahoo.com', '1900-02-02', 'male'),
	('Pepper Potts', 'pepperpotts@starkIn.com', '1975-03-04', 'female'),
	('Thor Ondinson', 'thorodinson@odin.od', '1000-01-01', 'male'),
	('Bruce Banner', 'brucebanner@gmail.com', '1980-05-10', 'male'),
	('Jennifer Walter', 'jenniferwalter@gmail.com', '1990-12-06', 'female'),
	('Luke Cage', 'lukecage@shield.com', '1985-06-10', 'male'),
	('Joker', 'joker@joker.jk', '1999-12-12', 'none'),
	('Harley Quinn', 'herleyquinn@gmail.com', '2000-01-01', 'female'),
	('Bruce Wayne', 'brucewayne@bw.com', '1990-10-20', 'male');
	
INSERT INTO Friendships (uid1, uid2, status)
VALUES
	(12,13,'friends'),
	(12,14,'friends'),
	(12,15,'friends'),
	(12,16,'friends'),
	(12,18,'friends'),
	(12,21,'friends'),
	(13,15,'firends'),
	(13,16,'friends'),
	(13,18,'firends'),
	(13,21,'pending'),
	(14,17,'friends'),
	(14,18,'friends'),
	(14,20,'blocked'),
	(15,16,'friends'),
	(15,17,'friends'),
	(15,18,'friends'),
	(16,17,'friends'),
	(16,18,'friends'),
	(16,19,'blocked'),
	(17,18,'friends'),
	(17,20,'blocked'),
	(18,21,'pending'),
	(19,20,'friends'),
	(19,21,'blocked'),
	(20,21,'blocked');

INSERT INTO Groups (name, description)
VALUES
	('S.H.I.E.L.D', 'Marvel Heroes'),
	('Squad', 'Just the two of us'),
	('Memers', 'Fun stuff'),
	('Science', 'Nerd stuff');

INSERT INTO GroupMemberships (uid,gid)
VALUES
	(12,1),
	(13,1),
	(14,1),
	(15,1),
	(16,1),
	(17,1),
	(18,1),
	(19,2),
	(20,2),
	(15,3),
	(18,3),
	(19,3),
	(12,4),
	(16,4),
	(21,4);

INSERT INTO Tags(name, description)
VALUES
	('fun','jokes'),
	('math',NULL),
	('travel', 'travel related things'),
	('food', 'cooking related things'),
	('lifestyle', 'every day activities'),
	('heroes', 'poeple doing good things'),
	('cars', 'tag for car lovers'),
	('technology', 'IT retaled things'),
	('discovery', 'discover new things'),
	('resist',NULL),
	('love', 'love stuff'),
	('couple_life', 'couple life');

INSERT INTO UserInterests (uid,tid)
VALUES
	(12,1),
	(12,2),
	(12,6),
	(12,7),
	(12,8),
	(12,9),
	(13,3),
	(13,5),
	(13,6),
	(13,9),
	(13,11),
	(14,1),
	(14,3),
	(14,5),
	(14,6),
	(14,8),
	(14,10),
	(14,11),
	(14,12),
	(15,1),
	(15,3),
	(15,4),
	(15,5),
	(15,6),
	(15,12),
	(16,2),
	(16,6),
	(16,8),
	(16,9),
	(17,1),
	(17,3),
	(17,9),
	(17,11),
	(17,12),
	(18,6),
	(18,7),
	(18,8),
	(18,10),
	(19,1),
	(19,7),
	(20,1),
	(20,5),
	(20,10),
	(20,11),
	(20,12),
	(21,2),
	(21,6),
	(21,7),
	(21,8),
	(21,9);
	
INSERT INTO Posts (authorId, title, text)
VALUES
	(12,'Stark Industry', 'Today I open stark industry'),
	(12,'F.R.I.D.A.Y', 'Female Replacement Intelligent Digital Assistant Youth'),
	(13, 'World War 2', 'My experience in ww2 was terrible'),
	(14, 'Healthy food', 'This is the healthiest recipe for a cake'),
	(14, 'Beach Day', 'My entire day at the beach'),
	(15, 'Best beers in town', 'Trying all the beers in town'),
	(16, 'Math discovery', 'I discovered a new math formula'),
	(16, 'Anti-aging cream', 'Discovered a cream anti-aging'),
	(17, 'My new boyfriend', 'Talking about my new boyfriend'),
	(18, 'Creating a new team', 'I am creating a new team'),
	(19, 'Very funny joke', 'The funniest joke on the internet'),
	(19, 'Another funny joke', 'The second funniest joke on the internet'),
	(20, 'Break the rules', 'I do not like the rules');

INSERT INTO PostTags (pid,tid)
VALUES
	(20,2),
	(20,3),
	(20,5),
	(20,6),
	(20,7),
	(20,8),
	(20,9),
	(21,2),
	(21,5),
	(21,9),
	(22,5),
	(22,6),
	(22,10),
	(23,1),
	(23,4),
	(23,5),
	(24,1),
	(24,5),
	(25,1),
	(25,5),
	(25,9),
	(25,11),
	(26,1),
	(26,2),
	(26,9),
	(27,5),
	(27,9),
	(27,10),
	(28,5),
	(28,11),
	(28,12),
	(29,6),
	(29,10),
	(30,1),
	(30,10),
	(31,1),
	(32,5),
	(32,10);


SELECT * FROM Users;
SELECT * FROM Posts;



INSERT INTO Likes (uid,pid)
VALUES
	(12,20),
	(12,21),
	(12,22),
	(12,23),
	(12,24),
	(12,26),
	(13,20),
	(13,21),
	(13,23),
	(13,26),
	(13,29),
	(14,20),
	(14,21),
	(14,23),
	(14,24),
	(14,27),
	(14,28),
	(14,30),
	(15,20),
	(15,25),
	(15,29),
	(15,30),
	(16,20),
	(16,22),
	(16,25),
	(17,23),
	(17,24),
	(17,27),
	(17,28),
	(17,30),
	(17,31),
	(19,25),
	(19,32),
	(20,30),
	(20,31),
	(20,32);

INSERT INTO Comments(uid,pid,content)
VALUES
	(12,22,'Wow'),
	(12,24,'Nice'),
	(12,30,'Haha'),
	(13,20,'Congrats!'),
	(13,29,'I am in!'),
	(14,20, 'Good job!'),
	(14,28,'Interesting!'),
	(16,20,'Good!'),
	(18,32,'Not ok!'),
	(19,32,'Nice'),
	(20,30,'Hahahah'),
	(20,31,'Hihi'),
	(21,32,'See o later')
	
	 
USE SocialMedia;

/* Tagurile de interes pentru userul Tony Stark */
SELECT T.name, T.description FROM Users as U
INNER JOIN UserInterests as UI ON UI.uid = U.uid
INNER JOIN Tags as T ON UI.tid = T.tid
WHERE U.username = 'Tony Stark'

/* Grupurile in case se afla Joker si numarul de membri ai grupurilor */
SELECT G.name AS GroupName, COUNT(G.name) NrMembers
FROM Groups AS G
LEFT JOIN GroupMemberships AS GM ON GM.gid = G.gid
WHERE G.name IN (SELECT GR.name FROM Groups as GR INNER JOIN GroupMemberships AS GMB ON GMB.gid = GR.gid INNER JOIN Users AS US ON US.uid = GMB.uid WHERE US.username='Joker')
GROUP BY G.name 

/* Cati prieteni are userul cu numele X in grupul Y */
SELECT COUNT(U.uid) AS Number
FROM Groups G
INNER JOIN GroupMemberships GM ON G.gid = GM.gid
INNER JOIN Users U ON U.uid = GM.uid
INNER JOIN Friendships FR ON FR.uid1 = U.uid OR FR.uid2 = U.uid
WHERE (FR.uid1 = 12 OR FR.uid2 = 12) AND FR.status = 'friends' AND G.name = 'S.H.I.E.L.D' AND U.uid <> 12

/* Numele tagurilor pe care ii intereseaza pe prietenii userului X*/
SELECT DISTINCT T.name, T.description
FROM Tags T
INNER JOIN UserInterests UI ON T.tid = UI.tid
INNER JOIN Users U ON U.uid = UI.uid
INNER JOIN Friendships FR ON FR.uid1 = U.uid OR FR.uid2 = U.uid
WHERE (FR.uid1 = 21 OR FR.uid2 = 21) AND FR.status = 'friends';

/* Postarile cu mai mult de 2 taguri */
SELECT P.title, COUNT(PT.pid) NrTags
FROM Posts P
LEFT JOIN PostTags PT ON PT.pid = P.pid
GROUP BY P.title
HAVING COUNT(PT.pid) > 2


/* Prietenii userului cu id-ul 12 care au dat like la postarea cu id-ul 20 */
SELECT U.username
FROM Posts P
INNER JOIN Likes L ON P.pid = L.pid
INNER JOIN Users U ON U.uid = L.uid
INNER JOIN Friendships F ON F.uid1 = U.uid OR F.uid2 = U.uid
WHERE F.status = 'friends' AND (F.uid1 = 12 OR F.uid2 = 12) AND U.uid <> 12 AND P.pid = 20;

/* CE taguri contin postarile la care a dat like userul X */
SELECT DISTINCT T.name
FROM Users U
INNER JOIN Likes L ON L.uid = U.uid
INNER JOIN Posts P ON L.pid = P.pid
INNER JOIN PostTags PT ON PT.pid = P.pid
INNER JOIN Tags T ON T.tid = PT.tid
WHERE U.username = 'Joker';

/*Userii care au dat mai mult de 1 comentariu la alte postari */
SELECT U.username, COUNT(U.uid) AS NrComments
FROM Users U
LEFT JOIN Comments C ON C.uid = U.uid
INNER JOIN Posts P ON P.pid = C.pid
GROUP BY U.username
HAVING COUNT(U.uid) > 1;

/* Suma likeurilor postarilor userului X */
SELECT COUNT(L.uid) AllLikes
FROM Users U
INNER JOIN Posts P ON U.uid = P.authorId
INNER JOIN Likes L ON L.pid = P.pid
WHERE U.username = 'Joker';



/* Numarul de comentarii acumulate de fiecare user la toate postarile sale */
SELECT COUNT(C.uid) as CommentsNumber, U.username
FROM Users U
INNER JOIN Posts P ON P.authorId= U.uid
INNER JOIN Comments C ON P.pid = C.pid
GROUP BY U.username;







