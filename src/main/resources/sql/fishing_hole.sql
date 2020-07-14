CREATE TABLE fishing_hole(
	id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40),
    category VARCHAR(40),
    address VARCHAR(80),
    latitude DECIMAL(12,10) UNSIGNED NOT NULL,
    longitude DECIMAL(13,10) UNSIGNED NOT NULL,
    fish_species VARCHAR(60),
    date DATE
)default character set utf8 collate utf8_general_ci;