package kr.reservation.vo;

public class ReservationVO {
	private int rev_num;
	private int p_num;
	private String rev_date;
	private String rev_time;
	private String r_ox;
	private int m_num;
	private int dr_num;
	
	public int getDr_num() {
		return dr_num;
	}
	public void setDr_num(int dr_num) {
		this.dr_num = dr_num;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getRev_date() {
		return rev_date;
	}
	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}
	public String getRev_time() {
		return rev_time;
	}
	public void setRev_time(String rev_time) {
		this.rev_time = rev_time;
	}
	public String getR_ox() {
		return r_ox;
	}
	public void setR_ox(String r_ox) {
		this.r_ox = r_ox;
	}
}
