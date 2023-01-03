create table reservation(
 rev_num number(10) not null,
 rev_date varchar2(20) not null,
 rev_time varchar2(20) not null,
 p_num number(10) not null,
 r_ox char default 'F' not null,
 constraint reservation_pk primary key (rev_num),
 constraint reservation_fk foreign key (p_num) references procedure
);

create sequence reservation_seq;