package kr.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.faq.vo.FaqVO;
import kr.util.DBUtil;
import kr.util.StringUtil;



public class FaqDAO {
		private static FaqDAO instance = new FaqDAO();
		
		public static FaqDAO getInstance() {
			return instance;
		}
		private FaqDAO() {}
		
		//faq 등록 
		public void insertFaq(FaqVO faq) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
			
				try {
					//커넥션풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
				
					//SQL문 작성
					sql = "INSERT INTO faq(faq_num,faq_title,"
							+ "faq_content,faq_type) VALUES ("
							+ "faq_seq.nextval,?,?,?)";
					
					//PreparedStatement 객체 생성
					pstmt = conn.prepareStatement(sql);
					//?에 데이터 바인딩
					pstmt.setString(1, faq.getFaq_title());
					pstmt.setString(2, faq.getFaq_content());
					pstmt.setString(3, faq.getFaq_type());	
					//SQL문 실행
					pstmt.executeUpdate();
				
				}catch(Exception e){
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
			
		}
		
		//faq 총 레코드 수
		public int getFaqCount()throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				
				int count = 0;
				
				try {
					conn = DBUtil.getConnection();
					
					sql = "SELECT COUNT(*) FROM faq";
					
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
		
		//faq 목록 
		public List<FaqVO> getListFaq (int start, int end) throws Exception{
			   Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   List<FaqVO> list = null;
			   String sql = null;

			   
			   
			   try {
				   conn = DBUtil.getConnection();
				   
				 
				   
				   sql = "SELECT * FROM (SELECT a.*, rownum rnum "
							+ "FROM (SELECT * FROM faq"
							+ " ORDER BY faq_num DESC)a) "
							+ "WHERE rnum >= ? AND rnum <= ?";
				   
				   pstmt = conn.prepareStatement(sql);
				  
				   pstmt.setInt(1, start);
				   pstmt.setInt(2, end);
				   
				   rs = pstmt.executeQuery();
				   list = new ArrayList<FaqVO>();
				   while(rs.next()) {
					   FaqVO faq = new FaqVO();
					   faq.setFaq_num(rs.getInt("faq_num"));
					   faq.setFaq_title(StringUtil.useNoHtml(rs.getString("faq_title")));
					   faq.setFaq_type(StringUtil.useNoHtml(rs.getString("faq_type")));
					   
					   list.add(faq);
				   }
				   
			   }catch (Exception e) {
				   throw new Exception(e);
			   }finally {
				   DBUtil.executeClose(rs, pstmt, conn);
			   }
			    return list;
		}
		//faq 글상세
		public FaqVO getFaq(int faq_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				FaqVO faq = null;
				String sql = null;
			
				try {
					conn = DBUtil.getConnection();
				    
					//추가
					sql = "SELECT * FROM faq WHERE faq_num=?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, faq_num);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						faq = new FaqVO();
						faq.setFaq_num(rs.getInt("faq_num"));
						faq.setFaq_title(rs.getString("faq_title"));
						faq.setFaq_content(rs.getString("faq_content"));
						faq.setFaq_type(rs.getString("faq_type"));
						
					}
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				return faq;
		}
		
		//faq 수정 
		public void updateFaq(FaqVO faq) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
	 //	   		 String sub_sql = "";
				int cnt = 0;
			
				try {
					conn = DBUtil.getConnection();
					
					sql="UPDATE faq SET faq_content= "
							+ "sub_sql WHERE faq_num";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(++cnt, faq.getFaq_title());
					pstmt.setString(++cnt, faq.getFaq_content());
					pstmt.setInt(++cnt, faq.getFaq_num());
					
					
					pstmt.executeUpdate();
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
		}
	
		//faq 삭제 
		public void deleteFaq(int faq_num) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt2 = null;
				PreparedStatement pstmt3 = null;
				String sql = null;
				
				try {
					
					conn = DBUtil.getConnection();
					//오토커밋 해제
					conn.setAutoCommit(false);
					
					//부모글 삭제
					sql = "DELETE FROM faq WHERE faq_num=?";
					pstmt3 = conn.prepareStatement(sql);
					pstmt3.setInt(1, faq_num);
					pstmt3.executeUpdate();
					
					//예외 발생 없이 정상적으로 SQL문이 실행
					conn.commit();
					
				}catch(Exception e) {
					conn.rollback();
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt3, null);
					DBUtil.executeClose(null, pstmt2, null);
					DBUtil.executeClose(null, pstmt, conn);
				}
				
		}
		
		
	
		
}

