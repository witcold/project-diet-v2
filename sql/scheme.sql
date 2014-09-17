CREATE TABLE users (
	user_id			BIGSERIAL,
	login			VARCHAR(256) NOT NULL,
	password		VARCHAR(40) NOT NULL,
	first_name		VARCHAR(40) NOT NULL,
	last_name		VARCHAR(40) NOT NULL,
	gender			CHAR(1) NOT NULL,
	birth_year		SMALLINT NOT NULL,
	height			SMALLINT NOT NULL,
	PRIMARY KEY (user_id),
	CONSTANT users_gender_check CHECK (gender IN ('m', 'f', 'u'))
);

CREATE TABLE weights (
	user_id			BIGINT NOT NULL,
	date			DATE NOT NULL,
	weight			FLOAT NOT NULL,
	PRIMARY KEY (user_id, date),
	FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE categories (
	category_id		BIGSERIAL,
	parent_id		BIGINT NOT NULL,
	name			VARCHAR(40) NOT NULL,
	PRIMARY KEY (category_id),
	FOREIGN KEY (parent_id) REFERENCES categories (category_id)
);

INSERT INTO categories (category_id, parent_id, name) VALUES (0, 0, 'NULL');

CREATE TABLE foods (
	food_id			BIGSERIAL,
	category_id		BIGINT NOT NULL,
	name			VARCHAR(80),
	calories		SMALLINT NOT NULL,
	proteins		SMALLINT NOT NULL,
	fats			SMALLINT NOT NULL,
	carbohydrates	SMALLINT NOT NULL,
	PRIMARY KEY (food_id),
	FOREIGN KEY (category_id) REFERENCES categories (category_id)
);

CREATE TABLE diaries (
	user_id			BIGINT NOT NULL,
	food_id			BIGINT NOT NULL,
	timestamp		TIMESTAMP NOT NULL,
	weight			FLOAT,
	PRIMARY KEY (user_id, food_id, timestamp),
	FOREIGN KEY (user_id) REFERENCES users (user_id),
	FOREIGN KEY (food_id) REFERENCES foods (food_id)
);
