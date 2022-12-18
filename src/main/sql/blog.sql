create sequence seq_blog202112004 increment by 1 start with 1;

create table blog202112004
(
    id  number(11) not null primary key,
    title   varchar2(50),
    content varchar2(100),
    author varchar2(30),
    email   varchar2(30) not null
);



ALTER table blog202112004 ADD create_date DATE DEFAULT SYSDATE;

INSERT INTO blog202112004 (id, author, email, title, content) VALUES
    (seq_blog202112004.nextval, 'John Smith',
     'john@example.com', 'My first blog post',
     'This is the content of my first blog post.');

COMMIT;
select * from blog202112004 ORDER BY create_date ASC;
select * from blog202112004 ORDER BY create_date DESC;
select * from blog202112004;

 update blog202112004 set title='인덕대', author='sw202112004', email='sw202112004@example.com',
                           content='ㅇㅇ' where id=1;


-- drop sequence seq_blog202112004;
--  drop table blog202112004;
