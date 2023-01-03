create table member(
  m_num number(10),
  id varchar2(10) unique not null,
  auth number(1) not null,
  constraint member_pk primary key(m_num)
);
create sequence member_seq;


create table member_detail(
  m_num number(10),
  name varchar2(10) not null,
  pwd varchar2(15) not null,
  email varchar2(30) not null,
  phone varchar2(15) not null,
  zipcode varchar2(5),
  address1 varchar2(50),
  address2 varchar2(50),
  imgsrc varchar2(50),
  content varchar2(4000),
  constraint member_detail_pk primary key(m_num),
  constraint member_detail_fk foreign key(m_num) references member(m_num)
);

create table information(
  info_num number(10),
  info_date date default sysdate not null,
  info_title varchar2(20) not null,
  info_content varchar2(1000) not null,
  m_num number(10) not null,
  constraint information_pk primary key(info_num),
  constraint information_fk foreign key(m_num) references member(m_num)
);
create sequence information_seq;