------------------------------------------------------------------------------------------
--users
------------------------------------------------------------------------------------------
DROP TABLE users CASCADE CONSTRAINTS;
--uesrs 테이블 삭제
drop table users;

--users 시퀀스 삭제
drop sequence seq_users_no;

--users table 생성
create table users (
    userNo      number,
    id          varchar2(50) unique not null,
    userName    varchar2(100) not null,
    password    varchar2(50) not null,
    joinDate    date not null,
    primary key(userNo)
);

--users 시퀀스 생성
create sequence seq_users_no
increment by 1
start with 1
nocache;

--users insert
insert into users
values (
    seq_users_no.nextval,
    'cjsrn1940',
    '차예진',
    '1234',
    sysdate
);

select *
from users;







------------------------------------------------------------------------------------------
--blog
------------------------------------------------------------------------------------------
--테이블 삭제
drop table blog;

--table 생성
create table blog (
    id          varchar2(50),
    blogTitle   varchar2(200) not null,
    logoFile    varchar2(200),
    primary key(id),
    constraint blog_fk foreign key (id)
    references users(id)
);
  
select  *
from blog b, users u
where b.id = u.id;


commit;
rollback;














------------------------------------------------------------------------------------------
--category
------------------------------------------------------------------------------------------
--테이블 삭제
drop table category;

--시퀀스 삭제
drop sequence seq_category_no;

--테이블 생성
create table category(
    cateNo          number,
    id              varchar2(50),
    cateName        varchar2(200) not null,
    description     varchar2(500),
    regDate         date not null,
    primary key(cateNo),
    constraint category_fk foreign key (id)
    references blog(id)
);

--시퀀스 생성
create sequence seq_category_no
increment by 1
start with 1
nocache;

select *
from category c, blog b
where c.id = b.id;

rollback;
commit;













------------------------------------------------------------------------------------------
--post
------------------------------------------------------------------------------------------
--테이블 삭제
drop table post;

--시퀀스 삭제
drop sequence seq_post_no;

--테이블 생성
create table post(
    postNo          number,
    cateNo          number,
    postTitle       varchar2(300) not null,
    postContent     varchar2(400),
    regDate         date not null,
    primary key(postNo),
    constraint post_fk foreign key (cateNo)
    references category(cateNo)
);

--시퀀스 생성
create sequence seq_post_no
increment by 1
start with 1
nocache;

select *
from category c, post p
where p.cateNo = C.cateNo;

rollback;
commit;





















------------------------------------------------------------------------------------------
--comments
------------------------------------------------------------------------------------------
--테이블 삭제
drop table comments;

--시퀀스 삭제
drop sequence seq_comments_no;

--테이블 생성
create table comments(
    cmtNo           number,
    postNo          number,
    userNo          number,
    cmtContent      varchar2(1000) not null,
    regDate         date not null,
    primary key(cmtNo),
    constraint comments_pN_fk foreign key (postNo)
    references post(postNo),
    constraint comments_uN_fk foreign key (userNo)
    references users(userNo)
);

--시퀀스 생성
create sequence seq_comments_no
increment by 1
start with 1
nocache;

select *
from comments c, users u, post p
where c.userNo = u.userNo
and c.postNo = p.postNo;

rollback;
commit;