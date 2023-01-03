create table review(
  r_num number(10),
  r_date date default sysdate not null,
  r_content varchar2(400) not null,
  r_imgsrc varchar2(20),
  star number(1) not null,
  rev_num number(10) not null,
  constraint review_pk primary key (r_num),
  constraint review_fk foreign key (rev_num) references reservation(rev_num)
 );
 
create sequence review_seq;
 


create table comments(
  c_num number(10),
  c_date date default sysdate not null,
  c_content varchar2(200) not null,
  constraint comments_pk primary key (c_num),
  m_num number(10) not null,
  r_num number(10) not null,
  constraint comments_fk foreign key (m_num) references qmember(m_num),
  constraint comments_fk2 foreign key (r_num) references review(r_num)
 );
 
 create sequence comments_seq;