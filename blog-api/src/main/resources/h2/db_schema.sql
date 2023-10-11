
DROP TABLE IF EXISTS keyword_search_info;

CREATE TABLE keyword_search_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    keyword VARCHAR(255) NOT NULL,
    count INT DEFAULT 0,
    status VARCHAR(10) DEFAULT 'NORMAL',
    last_search_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_keyword_search_info_1 ON keyword_search_info (count, status);
CREATE INDEX idx_keyword_search_info_2 ON keyword_search_info (last_search_date);