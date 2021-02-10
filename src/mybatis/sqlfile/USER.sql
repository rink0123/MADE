-- ======================== 사용자 설정 ========================

-- 사용자 설정은 SYS AS SYSDBA에서 생성할 것.

-- 사용자 삭제.
DROP USER MADE;
-- 사용자 삭제 및 생성.
CREATE USER MADE IDENTIFIED BY kosmo2020;

-- 사용자 모든 권한 삭제 및 부여.
REVOKE CONNCT, RESOURCE, DBA FROM MADE;
GRANT CONNECT, RESOURCE, DBA TO MADE;

-- 사용자 비밀번호 바꾸기.
ALTER USER MADE IDENTIFIED BY [바꿀 비밀번호 기입];

-- 개발용 데이터베이스 생성.
--DB_NAME     : MADE_TEST
--DB_USERID   : MADE_TEST
--DB_USERPW   : kosmo2020
--DB_HOSTNAME : localhost
--DB_PORT     : 1521
--DB_SID      : [자기 SID]