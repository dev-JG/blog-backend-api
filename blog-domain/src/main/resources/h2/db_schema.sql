
DROP TABLE IF EXISTS keyword_search_log;

CREATE TABLE keyword_search_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    keyword VARCHAR(255) NOT NULL,
    count INT DEFAULT 0,
    status VARCHAR(10) DEFAULT 'DISPLAY',
    last_search_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_keyword_search_info_1 ON keyword_search_log (count, status);
CREATE INDEX idx_keyword_search_info_2 ON keyword_search_log (last_search_date);
CREATE INDEX idx_keyword_search_info_3 ON keyword_search_log (keyword);