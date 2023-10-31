CREATE TABLE users (
	user_id BIGINT PRIMARY KEY,
	first_name varchar(255),
	last_name varchar(255)
);

INSERT INTO users (user_id, first_name, last_name)
VALUES
	(1, 'Tony', 'Stark'),
	(2, 'Steve', 'Rogers'),
	(3, 'Thor', 'Odinson'),
	(4, 'Bruce', 'Banner'),
	(5, 'Han', 'Solo'),
	(6, 'Obi-Wan', 'Kenobi'),
	(7, 'Luke', 'Skywalker'),
	(8, 'Darth', 'Vader'),
	(9, 'Jar-Jar', 'Binks');
	
SELECT * FROM users;