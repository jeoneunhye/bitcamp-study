/* distinct와 all */

/* 모든 데이터를 가져온다. */
select all loc from room;
select all loc, name from room;

/* all은 생략할 수 있다. */
select loc from room;

/* 중복 값을 한 개만 추출할 때 distinct를 붙인다. */
select distinct loc from room;

/* 컬럼이 2개 이상일 때
    그 컬럼들의 값이 중복될 경우만 중복 값으로 간주한다. */
select distinct loc, name from room;