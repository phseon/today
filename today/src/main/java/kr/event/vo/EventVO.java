package kr.event.vo;

import java.sql.Date;

public class EventVO {
	private int e_num;
	private String e_title;
	private String e_start;
	private String e_end;
	private String e_content;
	private String e_imgsrc;
	private Date e_date;
	private String e_thumb;
	private String e_resbtn;
	private int cal_date;
	private int m_num;
	
	public int getCal_date() {
		return cal_date;
	}
	public void setCal_date(int cal_date) {
		this.cal_date = cal_date;
	}
	public String getE_thumb() {
		return e_thumb;
	}
	public void setE_thumb(String e_thumb) {
		this.e_thumb = e_thumb;
	}
	public String getE_resbtn() {
		return e_resbtn;
	}
	public void setE_resbtn(String e_resbtn) {
		this.e_resbtn = e_resbtn;
	}
	public int getE_num() {
		return e_num;
	}
	public void setE_num(int e_num) {
		this.e_num = e_num;
	}
	public String getE_title() {
		return e_title;
	}
	public void setE_title(String e_title) {
		this.e_title = e_title;
	}
	public String getE_start() {
		return e_start;
	}
	public void setE_start(String e_start) {
		this.e_start = e_start;
	}
	public String getE_end() {
		return e_end;
	}
	public void setE_end(String e_end) {
		this.e_end = e_end;
	}
	public String getE_content() {
		return e_content;
	}
	public void setE_content(String e_content) {
		this.e_content = e_content;
	}
	public String getE_imgsrc() {
		return e_imgsrc;
	}
	public void setE_imgsrc(String e_imgsrc) {
		this.e_imgsrc = e_imgsrc;
	}
	public Date getE_date() {
		return e_date;
	}
	public void setE_date(Date e_date) {
		this.e_date = e_date;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	
	
}
