-- 실행될 때 마다 초기 데이터 삽입 SQL
INSERT INTO Idol VALUES (1, '임영웅'), (2, '이찬원'), (3, '장민호');
INSERT INTO Schedule VALUES (1, 1, 1, '2024-11-14 12:34:56.123456', '서울특별시 ~~', 'https://~~', '임영웅 콘서트', 'test description');
INSERT INTO User (userID, userName, profileImage) VALUES (12345, 'testUser1', 'https://~~'),
                                                         (67890, 'testUser2', 'https://~~');
INSERT INTO Ticketing (applicantID, scheduleID, ticketNum, seatingType, requestMessage)
VALUES(12345, 1, 3, 'Seated', 'test 요청 사항');

-- Schedule 데이터 삽입
INSERT INTO Schedule (scheduleID, idolID, scheduleName, scheduleDate, originUrl, description, location, isTicketing) VALUES
                                                                                                                         (2, 1, '임영웅 리사이틀 콘서트', '2024-11-20 20:00:00', 'https://ticket.interpark.com/webzine/paper/TPNoticeView.asp?bbsno=34&no=53378', '콘서트','서울특별시 고척스카이돔' 1),
                                                                                                                         (3, 1, '임영웅 브이로그 게시', '2024-11-20 21:00:00', 'https://www.youtube.com/watch?v=183Tzz1htyI', '브이로그', null, 0),
                                                                                                                         (4, 1, '임영웅 리사이틀 콘서트', '2024-12-27 19:30:00', 'https://ticket.interpark.com/webzine/paper/TPNoticeView.asp?bbsno=34&no=53378', '콘서트', '서울특별시 고척스카이돔', 1),
                                                                                                                         (5, 1, '임영웅 리사이틀 콘서트', '2024-12-28 17:00:00', 'https://ticket.interpark.com/webzine/paper/TPNoticeView.asp?bbsno=34&no=53378', '콘서트', '서울특별시 고척스카이돔', 1),
                                                                                                                         (6, 1, '아임히어로 임영웅 연애편지 공개', '2024-11-04 08:00:00', 'https://x.com/limyoungwoong/status/1853210865207759202', '영화 개봉', null, 0);