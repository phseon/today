create table procedure(
 m_num number(10) not null,
 p_num number(10),
 p_title varchar2(20) not null,
 p_content varchar2(1000) not null,
 p_imgsrc varchar2(200),
 constraint procedure_pk primary key (p_num),
 constraint procedure_fk foreign key (m_num) references member (m_num)
);
create sequence procedure_seq;