package kr.information.vo;

import java.sql.Date;

public class InformationVO {
	private int info_num;
	private Date info_date;
	private String info_title;
	private String info_content;
	private int m_num;
	
	private String id;

	
	public int getInfo_num() {
		return info_num;
	}

	public void setInfo_num(int info_num) {
		this.info_num = info_num;
	}

	public Date getInfo_date() {
		return info_date;
	}

	public void setInfo_date(Date info_date) {
		this.info_date = info_date;
	}

	public String getInfo_title() {
		return info_title;
	}

	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}

	public String getInfo_content() {
		return info_content;
	}

	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
