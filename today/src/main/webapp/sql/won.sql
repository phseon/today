create table faq (
    faq_num      number(10)      not null,
    faq_title    varchar2(100)   not null,
    faq_content  varchar2(3000)  not null,
    faq_type     varchar2(12)    not null,
    constraint faq_pk primary key (faq_num)
);
 create sequence faq_seq;



create table qna (
    
    qna_num     number(10)       not null,
    qna_type    varchar2(12)     not null,
    qna_title   varchar2(100)    not null,
    q_content   varchar2(3000)   not null,
    a_content   varchar2(3000),
    q_date  date  default sysdate  not null,
    a_date  date,
    m_num number(10) not null,
    constraint qna_pk primary key (qna_num),
    constraint qna_fk foreign key (m_num) references member (m_num)
);
 create sequence qna_seq;