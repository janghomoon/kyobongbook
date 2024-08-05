INSERT INTO categories (category_id, category_name)
SELECT category_id, category_name
FROM (SELECT 1 as category_id, '문학' as category_name) t
WHERE NOT EXISTS (SELECT 1 FROM categories c WHERE c.category_id = t.category_id and c.category_name = t.category_name);

INSERT INTO categories (category_id, category_name)
SELECT category_id, category_name
FROM (SELECT 2 as category_id, '경제경영' as category_name) t
WHERE NOT EXISTS (SELECT 1 FROM categories c WHERE c.category_id = t.category_id and c.category_name = t.category_name);

INSERT INTO categories (category_id, category_name)
SELECT category_id, category_name
FROM (SELECT 3 as category_id, '인문학' as category_name) t
WHERE NOT EXISTS (SELECT 1 FROM categories c WHERE c.category_id = t.category_id and c.category_name = t.category_name);

INSERT INTO categories (category_id, category_name)
SELECT category_id, category_name
FROM (SELECT 4 as category_id, 'IT' as category_name) t
WHERE NOT EXISTS (SELECT 1 FROM categories c WHERE c.category_id = t.category_id and c.category_name = t.category_name);

INSERT INTO categories (category_id, category_name)
SELECT category_id, category_name
FROM (SELECT 5 as category_id, '과확' as category_name) t
WHERE NOT EXISTS (SELECT 1 FROM categories c WHERE c.category_id = t.category_id and c.category_name = t.category_name);




INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '권태영' as author, 1 as is_available, '너에게 해주지 못한 말들' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '현영서' as author, 1 as is_available, '단순하게 배부르게' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '권태영' as author, 1 as is_available, '게으른 사랑' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '권태영' as author, 1 as is_available, '트랜드 코리아 2322' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '장동혁' as author, 1 as is_available, '초격자 투자' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '홍길동' as author, 1 as is_available, '파이어족 강환국의 하면 되지 않는다! 퀀트 투자' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '이서연' as author, 1 as is_available, '진심보다 밥' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '위성원' as author, 1 as is_available, '실패에 대하여 생각하지 마라' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '지승열' as author, 1 as is_available, '실리콘밸리 리더십 쉽다' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '지승열' as author, 1 as is_available, '데이터분석을 위한 A 프로그래밍' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);


INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '장동혁' as author, 1 as is_available, '인공지능1-12' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);

INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '위성원' as author, true as is_available, '-1년차 게임 개발' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);


INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '권태영' as author, true as is_available, 'Skye가 알려주는 피부 채색의 비결' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);


INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '장지명' as author, true as is_available, '자연의 발전' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);


INSERT INTO books (author, is_available, title)
SELECT author, is_available, title
FROM (SELECT '이승열' as author, true as is_available, '코스모스 필 무렵' as title) t
WHERE NOT EXISTS (SELECT 1 FROM books b WHERE b.author= t.author and b.title = t.title);