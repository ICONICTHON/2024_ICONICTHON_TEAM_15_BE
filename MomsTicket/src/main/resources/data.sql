-- 실행될 때 마다 초기 데이터 삽입 SQL
INSERT INTO Idol VALUES (1, '임영웅'), (2, '이찬원'), (3, '장민호');
INSERT INTO Schedule VALUES (1, 1, 1, '2024-11-14 12:34:56.123456', '서울특별시 ~~', 'https://~~', '임영웅 콘서트', 'test description');
INSERT INTO User (userID, userName, profileImage) VALUES (12345, 'testUser1', 'https://~~'),
                                                         (67890, 'testUser2', 'https://~~');
INSERT INTO Ticketing (applicantID, scheduleID, ticketNum, seatingType, requestMessage)
VALUES(12345, 1, 3, 'Seated', 'test 요청 사항');