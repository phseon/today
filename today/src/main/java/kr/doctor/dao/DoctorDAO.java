package kr.doctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class DoctorDAO {
	private static DoctorDAO instance = new DoctorDAO();
	public static DoctorDAO getInstance() {
		return instance;
	}
	
	private DoctorDAO() {}
	
	public int getDoctorCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM member m "
			+ "JOIN member_detail d ON m.m_num=d.m_num "
			+ "WHERE auth=1";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
			count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	public List<MemberVO> getDoctor(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO doctor = null;
		List<MemberVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM member m JOIN "
					+ "member_detail d USING(m_num) WHERE "
					+ "m.auth=1 ORDER BY m_num DESC)a) "
					+ "WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<MemberVO>();
			while(rs.next()) {
				doctor = new MemberVO();
				doctor.setM_num(rs.getInt("m_num"));
				doctor.setName(rs.getString("name"));
				doctor.setImgsrc(rs.getString("imgsrc"));
				
				list.add(doctor);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public MemberVO getDoctorDetail(int m_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO doctor = null;
		String sql = null;
		
		try{
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member_detail WHERE m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				doctor = new MemberVO();
				doctor.setM_num(rs.getInt("m_num"));
				doctor.setName(rs.getString("name"));
				doctor.setContent(rs.getString("content"));
				doctor.setImgsrc(rs.getString("imgsrc"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return doctor;
	}
}
