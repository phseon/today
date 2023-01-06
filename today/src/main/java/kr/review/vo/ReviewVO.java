package kr.review.vo;

import java.sql.Date;

public class ReviewVO {
	private int r_num;
	private Date r_date;
	private String r_content;
	private String r_imgsrc;
	private int star;
	private int rev_num;
	
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
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
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
}