package kr.review.vo;

import java.sql.Date;

public class ReviewVO {
	private int r_num;
	private Date r_date;
	private String r_content;
	private String r_imgsrc;
	private int star;
	private int rev_num;
	private String rev_date;
	private String rev_time;
	private int m_num;
	private String dr_name;
	private String p_title;
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public Date getR_date() {
		return r_date;
	}	
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public String getRev_date() {
		return rev_date;
	}
	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getRev_time() {
		return rev_time;
	}
	public void setRev_time(String rev_time) {
		this.rev_time = rev_time;
	}
	public String getR_imgsrc() {
		return r_imgsrc;
	}
	public void setR_imgsrc(String r_imgsrc) {
		this.r_imgsrc = r_imgsrc;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}		
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}		
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String name) {
		this.dr_name = name;
	}		
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}		
}