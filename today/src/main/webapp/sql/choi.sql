create table event(
 e_num number not null,
 e_title varchar2(50) not null,
 e_start varchar2(20) not null,
 e_end varchar2(20) not null,
 e_content varchar2(4000) not null,
 e_imgsrc varchar2(500),
 e_date date default SYSDATE not null,
 m_num number not null,
 constraint event_pk primary key (e_num),
 constraint event_fk foreign key (m_num) references member (m_num)
);

create sequence event_seq;