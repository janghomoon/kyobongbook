CREATE TABLE IF NOT EXISTS categories
(
    category_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE COMMENT '카테고리명',
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    updated_at    TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);


CREATE TABLE IF NOT EXISTS books
(
    book_id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    title                VARCHAR(1000) NOT NULL COMMENT '책 제목',
    author               VARCHAR(1000) NOT NULL COMMENT '책 저자',
    is_available         BOOLEAN       NOT NULL DEFAULT 1 COMMENT '책 대여 가능 여부',
    not_available_reason VARCHAR(1000) NULL     DEFAULT NULL COMMENT '책 대여 불가 사유',
    created_at           TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at           TIMESTAMP     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS book_categories
(
    book_id     BIGINT COMMENT '책 아이디',
    category_id BIGINT COMMENT '카테고리 아이디',
    unique KEY (book_id, category_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id),
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);