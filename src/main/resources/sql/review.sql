CREATE TABLE review(
	id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    fish_id BIGINT UNSIGNED NOT NULL,
    user_id BIGINT UNSIGNED NOT NULL,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(60) NOT NULL,
    content TEXT(100) NOT NULL,
    created_at DATETIME DEFAULT current_timestamp(),
    update_at DATETIME,
    FOREIGN KEY(fish_id) REFERENCES fishing_hole(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
)default character set utf8 collate utf8_general_ci;