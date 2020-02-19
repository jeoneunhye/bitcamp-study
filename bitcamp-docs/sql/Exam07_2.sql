/* order by */

/* 입력된 순서가 아닌, 기본 인덱스 컬럼을 기준으로 정렬한다. */
select rno, loc, name 
from room;

/* 이름을 오름차순으로 정렬하기 */
select rno, loc, name 
from room
order by name asc;

/* asc는 생략 가능하다. */
select rno, loc, name 
from room
order by name;

/* 이름을 내림차순으로 정렬하기 */
select rno, loc, name 
from room
order by name desc;

/* 이름을 오름차순으로 정렬한 다음 이름이 같으면 지점명도 오름차순으로 정렬하기 */
select rno, loc, name 
from room
order by name asc, loc asc;

/* 이름을 오름차순으로 정렬한 다음 이름이 같으면 지점명을 내림차순으로 정렬하기 */
select rno, loc, name 
from room
order by name asc, loc desc;

/* 지점명을 오름차순으로 정렬한 다음 지점명이 같으면 이름을 오름차순으로 정렬하기 */
select rno, loc, name 
from room
order by loc asc, name asc;