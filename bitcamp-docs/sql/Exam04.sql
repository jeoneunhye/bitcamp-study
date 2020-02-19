# FK(Foreign Key)
- 다른 테이블의 PK를 참조하는 컬럼이다.

/* 게시판 테이블 */
> create table test1(
  no int not null primary key auto_increment, /* 게시물 번호 */
  title varchar(255) not null, /* 게시물 제목 */
  content text, /* 게시물 내용 */
  rdt datetime not null default now() /* 게시물 등록일 */
);

/* 첨부 파일 테이블 */
> create table test2(
  fno int not null primary key auto_increment, /* 첨부파일 고유번호 */
  filepath varchar(255) not null, /* 파일시스템에 저장된 첨부파일의 경로 */
  bno int not null /* 게시물 번호 */
);

게시판 데이터 입력:
> insert into test1(title) values('aaa');
insert into test1(title) values('bbb');
insert into test1(title) values('ccc');
insert into test1(title) values('ddd');
insert into test1(title) values('eee');
insert into test1(title) values('fff');
insert into test1(title) values('ggg');
insert into test1(title) values('hhh');
insert into test1(title) values('iii');
insert into test1(title) values('jjj');

첨부 파일 데이터 입력:
> insert into test2(filepath, bno) values('c:/download/a.gif', 1);
insert into test2(filepath, bno) values('c:/download/b.gif', 1);
insert into test2(filepath, bno) values('c:/download/c.gif', 1);
insert into test2(filepath, bno) values('c:/download/d.gif', 5);
insert into test2(filepath, bno) values('c:/download/e.gif', 5);
insert into test2(filepath, bno) values('c:/download/f.gif', 10);

## FK 제약 조건이 없을 때
1. 첨부 파일 데이터를 입력할 때 존재하지 않는 게시물 번호가 들어갈 수 있다.
- 그러면 첨부 파일 데이터는 무효한 데이터가 된다.
> insert into test2(filepath, bno) values('c:/download/x.gif', 100); -- 존재하지 않는 게시물 번호

2. 첨부 파일이 있는 게시물을 삭제할 수 있다.
- 마찬가지로 게시물을 참조하는 첨부 파일 데이터는 무효한 데이터가 된다.
> delete from test1 where no=1;
- 이런 문제가 발생한 이유?
- 다른 테이블을 참조할 때 참조하는 데이터가 존재하는지 유무를 검사하는 제약을 걸지 않았기 때문이다.
- 즉 테이블의 데이터를 삭제할 때 다른 테이블이 참조하는지 여부를 검사하지 않기 때문이다.

- 해결책?
- 다른 데이터를 참조하는 경우 해당 데이터의 존재 유무를 검사하도록 강제한다.
- 다른 데이터를 참조하는지 여부를 검사하도록 "외부키(Foreign Key)" 문법을 사용한다.

## FK(foreign key) 제약 조건 설정
- 다른 테이블의 데이터와 연관된 데이터를 저장할 때 무효한 데이터가 입력되지 않도록 하는 문법이다.
- 다른 테이블의 데이터가 참조하는 데이터를 임의로 지우지 못하도록 하는 문법이다.
- 또한 외래키로 사용되고 있는 다른 테이블의 기본키는 삭제할 수 없도록 잠금되어 있다.
- 그래서 데이터의 무결성(data integrity; 결함이 없는 상태)을 유지하게 도와주는 문법이다.


다른 테이블의 PK를 참조하는 컬럼으로 선언한다.
alter table 테이블명
    add constraint 제약조건이름 foreign key (컬럼명) references 테이블명(컬럼명);

예)
/* 기존 테이블에 무효한 데이터가 있을 수 있기 때문에 먼저 참조하는 자식 테이블의 데이터를 지운다. */
> delete from test2;

> alter table test2
    add constraint test2_bno_fk foreign key (bno) references test1(no);

위와 같이 test2 테이블에 FK 제약 조건을 건 다음에 데이터를 입력해보자!
/* 현재 1번 게시물이 존재하지 않기 때문에 데이터를 입력할 수 없다. */
/* 입력 오류 Cannot add or update a child row: a foreign key constraint fails */
> insert into test2(filepath, bno) values('c:/download/a.gif', 1);
insert into test2(filepath, bno) values('c:/download/b.gif', 1);
insert into test2(filepath, bno) values('c:/download/c.gif', 1);

/* 5번, 10번 게시물은 존재하기 때문에 첨부파일 데이터를 입력할 수 있다. */
> insert into test2(filepath, bno) values('c:/download/d.gif', 5);
insert into test2(filepath, bno) values('c:/download/e.gif', 5);
insert into test2(filepath, bno) values('c:/download/f.gif', 10);

/* 2번 게시물은 test2 테이블의 데이터들이 참조하지 않기 때문에 마음대로 지울 수 있다. */
> delete from test1 where no=2; -- OK!

/* 그러나 5번 게시물은 삭제할 수 없다. 왜? test2 테이블의 데이터 중 일부가 참조하기 때문이다. */
/* 삭제 오류 Cannot delete or update a parent row: a foreign key constraint fails */
> delete from test1 where no=5; -- Error!

/* 회원을 탈퇴했다고 해서 게시물이 지워지는 것이 아니다. 임의의 정보를 부여하여 기존에 작성한 글은 유지한다. */

## 용어 정리
- 데이터가 여러 테이블에서 서로 관계를 맺고 있는 RelationalDBMS = RDBMS
- test1처럼 다른 테이블에 의해 참조되는 테이블을 '부모 테이블(parent table)'이라 부른다.
- test2처럼 외래키가 있어 다른 테이블의 데이터를 참조하는 테이블을 '자식 테이블(child table)'이라 부른다.
=> 자식 테이블에서 부모 테이블을 사용하는 다대 일 관계