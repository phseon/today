package kr.doctor.vo;

public class DoctorVO {
	private int m_num;
	private int p_num;
	private String p_title;
	private String p_content;
	private String p_imgsrc;
	private String dr_name;
	
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_imgsrc() {
		return p_imgsrc;
	}
	public void setP_imgsrc(String p_imgsrc) {
		this.p_imgsrc = p_imgsrc;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	
}
