package kr.procedure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import kr.procedure.vo.ProcedureVO;
import kr.util.DBUtil;

public class ProcedureDAO {
	//싱들턴 패턴
		private static ProcedureDAO instance = new ProcedureDAO();
		
		public static ProcedureDAO getInstance() {
			return instance;
		}
		private ProcedureDAO() {}
		
		//글생성
		public void insertProcedure(ProcedureVO pro)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "INSERT INTO procedure (p_num,m_num,p_title,p_content,p_imgsrc) "
						+ "VALUES (procedure_seq.nextval,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pro.getM_num());
				pstmt.setString(2, pro.getP_title());
				pstmt.setString(3, pro.getP_content());
				pstmt.setString(4, pro.getP_imgsrc());
				
				pstmt.executeUpdate();
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//글 카운트
		public int getCount()throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT count(*) FROM procedure";
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
		//글목록
		public List<ProcedureVO> select(int startRow, int endRow)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ProcedureVO> list = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM procedure";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				list = new ArrayList<ProcedureVO>();
				while(rs.next()) {
					ProcedureVO pro = new ProcedureVO();
					pro.setM_num(rs.getInt("m_num"));
					pro.setP_num(rs.getInt("p_num"));
					pro.setP_title(rs.getString("p_title"));
					pro.setP_content(rs.getString("p_content"));
					pro.setP_imgsrc(rs.getString("p_imgsrc"));
					list.add(pro);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		public ProcedureVO getProcedure(int p_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			ProcedureVO pro = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM procedure WHERE p_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, p_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					pro = new ProcedureVO();
					pro.setM_num(rs.getInt("m_num"));
					pro.setP_num(rs.getInt("p_num"));
					pro.setP_title(rs.getString("p_title"));
					pro.setP_content(rs.getString("p_content"));
					pro.setP_imgsrc(rs.getString("p_imgsrc"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return pro;
		}
		public void updateProcedure(ProcedureVO pro)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			String sub_sql ="";
			int cnt = 0;
			
			try {
				conn = DBUtil.getConnection();
				if(pro.getP_imgsrc()!=null) {//파일네임이 있으면 처리 없으면 처리를 안한다
					sub_sql +=",p_imgsrc=?";
				}
				sql ="UPDATE procedure SET p_title=?, p_content=?"+ sub_sql + "WHERE p_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, pro.getP_title());
				pstmt.setString(++cnt, pro.getP_content());
				if(pro.getP_imgsrc()!=null) {
					pstmt.setString(++cnt, pro.getP_imgsrc());
				}
				pstmt.setInt(++cnt, pro.getP_num());
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		public void delteImage(int p_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE procedure SET p_imgsrc='' WHERE p_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, p_num);
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
			
		}
		public void deleteProcedure(int p_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "DELETE FROM procedure WHERE p_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, p_num);
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		public String pwdProcedure(int m_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String pwd = "";
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT m.pwd FROM procedure p JOIN member_detail m ON p.m_num=m.m_num "
						+ "WHERE p.m_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					pwd = rs.getString(1);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return pwd;
		}
		/*
		public List<MemberVO> doctor()throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<MemberVO> list = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT md.m_num,md.name FROM member m JOIN member_detail md on m.m_num=md.m_num "
						+ "WHERE m.auth=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				rs = pstmt.executeQuery();
				list = new ArrayList<MemberVO>();
				while(rs.next()) {
					MemberVO m = new MemberVO();
					m.setM_num(rs.getInt("m_num"));
					m.setName(rs.getString("name"));
					list.add(m);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		*/
}
