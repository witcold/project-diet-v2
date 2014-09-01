CREATE TABLE users (
	user_id			BIGSERIAL,
	login			VARCHAR(256) NOT NULL,
	password		VARCHAR(40) NOT NULL,
	first_name		VARCHAR(40) NOT NULL,
	last_name		VARCHAR(40) NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE weights (
	user_id			BIGINT NOT NULL,
	date			DATE NOT NULL,
	weight			FLOAT NOT NULL,
	PRIMARY KEY (user_id, date),
	FOREIGN KEY (user_id) REFERENCES users (user_id)
);
