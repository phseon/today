package kr.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.doctor.vo.DoctorVO;
import kr.reservation.vo.ReservationVO;
import kr.util.DBUtil;

public class ReservationDAO {
	private static ReservationDAO instance = new ReservationDAO();
	
	public static ReservationDAO getInstance() {
		return instance;
	}
	
	private ReservationDAO() {}
	
	//시술 찾기
	public List<DoctorVO> getProcedureList()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		DoctorVO doctor = null;
		List<DoctorVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM procedure p JOIN member_detail d ON p.m_num=d.m_num ORDER BY p_num DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<DoctorVO>();
			while(rs.next()) {
				doctor = new DoctorVO();
				doctor.setM_num(rs.getInt("m_num"));
				doctor.setP_num(rs.getInt("p_num"));
				doctor.setP_title(rs.getString("p_title"));
				doctor.setDr_name(rs.getString("name"));
				
				list.add(doctor);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//예약 등록
	public void insertReservation(ReservationVO reserve)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO reservation (rev_num,rev_date,rev_time,p_num,"
					+ "m_num,dr_num) VALUES (reservation_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserve.getRev_date());
			pstmt.setString(2, reserve.getRev_time());
			pstmt.setInt(3, reserve.getP_num());
			pstmt.setInt(4, reserve.getM_num());
			pstmt.setInt(5, reserve.getDr_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//예약 조회
	public ReservationVO getReservation(int m_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ReservationVO reserve = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM reservation WHERE m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reserve = new ReservationVO();
				reserve.setM_num(rs.getInt("m_num"));
				reserve.setRev_num(rs.getInt("rev_num"));
				reserve.setRev_date(rs.getString("rev_date"));
				reserve.setRev_time(rs.getString("rev_time"));
				reserve.setR_ox(rs.getString("r_ox"));
				reserve.setP_num(rs.getInt("p_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return reserve;
	}
}
