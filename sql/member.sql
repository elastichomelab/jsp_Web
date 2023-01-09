create sequence seq_member increment by 1 start with 1;

create table member (
                        id number(11) not null primary key,
                        email varchar2(30) not null unique,
                        pw varchar2(20) not null,
                        name varchar2(30) not null,
                        phone varchar2(30),
                        address varchar2(50)
);

commit;

INSERT INTO member values (seq_member.nextval, 'sw202112004@induk.ac.kr', 'cometrue','sw202112004', '1234', '1234');
INSERT INTO member values (seq_member.nextval, 'dbstjdghks25@gmail.com', 'cometrue','dbstjdghks', '1234', '1234');
INSERT INTO member values (seq_member.nextval, 'dodo@test.com', 'cometrue','dodo', '1234', '1234');


SELECT * FROM member;
COMMIT;

-- DROP sequence seq_member;

-- DROP TABLE member;
