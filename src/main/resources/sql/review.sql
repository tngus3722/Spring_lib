CREATE TABLE review(
	id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    fish_id SMALLINT UNSIGNED NOT NULL,
    name VARCHAR(20) NOT NULL,
    title VARCHAR(60) NOT NULL,
    content TEXT(100) NOT NULL,
    created_at DATETIME DEFAULT current_timestamp(),
    update_at DATETIME ,
    FOREIGN KEY(fish_id) REFERENCES fishing_hole(id)
)default character set utf8 collate utf8_general_ci;