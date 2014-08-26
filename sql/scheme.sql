CREATE TABLE users (
	login			VARCHAR(20) NOT NULL,
	password		VARCHAR(80) NOT NULL,
	first_name		VARCHAR(20) NOT NULL,
	last_name		VARCHAR(20) NOT NULL,
	PRIMARY KEY (login)
);

