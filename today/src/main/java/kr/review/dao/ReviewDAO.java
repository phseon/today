package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.member.vo.MemberVO;
import kr.reservation.vo.ReservationVO;
import kr.review.vo.ReviewCommVO;
import kr.review.vo.ReviewVO;
import kr.procedure.vo.ProcedureVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class ReviewDAO {
	private static ReviewDAO instance = new ReviewDAO();
	
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	private ReviewDAO() {}
	
	//리뷰 등록
	public void insertReivew(ReviewVO review, int rev_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);

//			sql = "INSERT INTO review(r_num,r_date,"
//				+ "r_content,r_imgsrc,star,rev_num) VALUES ("
//				+ "review_seq.nextval(),SYSDATE,?,?,?,?)";

//			sql = "INSERT INTO review(r_num,r_date,"
//					+ "r_content,r_imgsrc) VALUES ("
//					+ "review_seq.nextval,SYSDATE,?,?,?)";
			
			//별점, 이미지 추가  
			sql = "INSERT INTO review(r_num,r_date,"
				+ "r_content,r_imgsrc,star,rev_num) VALUES "
				+ "(review_seq.nextval,SYSDATE,?,?,?,?)";    
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getR_content());
			pstmt.setString(2, review.getR_imgsrc());
			pstmt.setInt(3, review.getStar());
			pstmt.setInt(4, rev_num);
			pstmt.executeUpdate();
			
			sql = "UPDATE reservation "
				+ "SET r_ox ='T' "
				+ "WHERE rev_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, rev_num);
			pstmt2.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
			DBUtil.executeClose(null, pstmt2, conn);
		}
	}
	
	//총 레코드 수
		public int getReviewCount(String keyfield, String keyword)
		                                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			int count = 0;
			
			try {
				conn = DBUtil.getConnection();
				
				if(keyword != null && !"".equals(keyword)) {
					if(keyfield.equals("1")) sub_sql += "WHERE r.r_content LIKE ?";
					else if(keyfield.equals("2")) sub_sql += "WHERE m.name LIKE ?";
					else if(keyfield.equals("3")) sub_sql += "WHERE p.p_title LIKE ?";
				}
				
				//SQL문 작성
				sql = "SELECT COUNT(*) " 
					+ "FROM reservation v "
					+ "JOIN procedure p ON v.p_num=p.p_num "
					+ "JOIN review r ON v.rev_num=r.rev_num " 
					+ "JOIN member_detail m ON p.m_num=m.m_num "
					+ sub_sql;
				 
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				if(keyword !=null && !"".equals(keyword)) {
					pstmt.setString(1, "%" + keyword + "%");
				}

				//SQL문을 실행하고 결과행을 ResultSet 담음
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
	
	//리뷰 목록
	public List<ReviewVO> getListReview(int start, int end,
            							String keyfield, String keyword)
            									throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE r.r_content LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.name LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE p.p_title LIKE ?";
			}
			
			//SQL문 작성
//			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
//				+ "FROM	(SELECT * FROM review r "
//				+ sub_sql
//				+ "ORDER BY r.r_num DESC)a) "
//				+ "WHERE rnum >= ? AND rnum <= ?";
			
			
			
			
			sql = "SELECT r_content, name, p_title, r_num, r_date, star "
				+ " FROM (SELECT a.*, rownum rnum "
				+ "FROM (SELECT * FROM reservation v "
				+ "JOIN procedure p ON v.p_num=p.p_num "
				+ "JOIN review r ON v.rev_num=r.rev_num "
				+ "JOIN member_detail m ON p.m_num=m.m_num "
				+ sub_sql
				+ "ORDER BY r.r_num DESC)a) "
				+ "WHERE rnum >=? AND rnum <=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//SQL문을 실행해서 결과행들을 ResultSet에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewVO>();
			while(rs.next()) {
				ReviewVO review = new ReviewVO();
				review.setR_num(rs.getInt("r_num"));
				review.setR_content(StringUtil.useNoHtml(rs.getString("r_content")));
//				review.setR_imgsrc(rs.getString("r_imgsrc"));
				review.setR_date(rs.getDate("r_date"));
				review.setDr_name(rs.getString("name"));
				review.setP_title(rs.getString("p_title"));
				review.setStar(rs.getInt("star"));
				
				list.add(review);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
			return list;
		}
	
	
	//리뷰상세
	public ReviewVO getReview(int r_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
//		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ReviewVO review = null;
//		ReservationVO rez = null;
//		ReviewCommVO comm = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//오토커밋 해제
			conn.setAutoCommit(false);
			
			//SQL문 작성
//			sql = "SELECT * FROM review r JOIN reservation v "
//				+ "USING(rev_num) JOIN comments c USING(r_num) WHERE r.r_num=?";
//			
			
//			sql = "SELECT * FROM review r LEFT JOIN reservation v " 
//				+ "ON r.rev_num=v.rev_num "
//				+ "LEFT JOIN comments c "
//				+ "ON r.r_num=c.r_num "
//				+ "WHERE r.r_num=?";

			//리뷰를 작성한 회원 예약 정보까지 출력
			sql = "SELECT v.m_num, m.name, v.*,p.*, r.*, m.* "
				+ "FROM reservation v "
				+ "JOIN procedure p ON v.p_num=p.p_num "
				+ "JOIN review r ON v.rev_num=r.rev_num "
				+ "JOIN member_detail m ON p.m_num=m.m_num "
				+ "WHERE r.r_num=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, r_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review = new ReviewVO();
//				rez = new ReservationVO();
				review.setR_num(rs.getInt("r_num"));
				review.setR_content(rs.getString("r_content"));
				review.setR_date(rs.getDate("r_date"));
				review.setRev_num(rs.getInt("rev_num"));
				review.setRev_date(rs.getString("rev_date"));
				review.setRev_time(rs.getString("rev_time"));
				review.setRev_num(rs.getInt("rev_num"));
				review.setM_num(rs.getInt("m_num"));
				review.setDr_name(rs.getString("name"));
				review.setP_title(rs.getString("p_title"));
				review.setR_imgsrc(rs.getString("r_imgsrc"));
				review.setStar(rs.getInt("star"));
			}
			
//			sql = "SELECT * FROM review r JOIN comments c "
//				+ "USING(r_num) WHERE r.r_num=?";
//			pstmt2 = conn.prepareStatement(sql);
//			pstmt2.setInt(1, r_num);
//			rs = pstmt2.executeQuery();
//			if(rs.next()) {
//				review.setR_num(rs.getInt("r_num"));
//			}
//			
//			//sql 실행시 모두 성공하면 commit
//			conn.commit();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
	}
	
	
	//파일 삭제
		public void deleteFile(int r_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE review SET r_imgsrc='' WHERE r_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, r_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		
	//리뷰수정
	public void updateReview(ReviewVO review)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//전송된 파일 여부 체크
			if(review.getR_imgsrc()!=null) {
				sub_sql += ",r_imgsrc=? ";
			}
			
			sql = "UPDATE review SET r_content=? "
				+ sub_sql 
				+ ",star=?"
				+ "WHERE r_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, review.getR_content());
			if(review.getR_imgsrc()!=null) {
				pstmt.setString(++cnt, review.getR_imgsrc());
			}
			pstmt.setInt(++cnt, review.getStar());
			pstmt.setInt(++cnt, review.getR_num());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	
	//리뷰 삭제
	public void deleteReview(int r_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//오토커밋 해제
			conn.setAutoCommit(false);
			
			sql = "UPDATE reservation "
					+ "SET r_ox ='F' " 
					+ "WHERE rev_num = (SELECT v.rev_num "
					+ "FROM reservation v, review r "
	                + "WHERE v.rev_num=r.rev_num "
	                + "AND r.r_num=?)";
	            pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r_num);
				pstmt.executeUpdate();
				
			//부모글 삭제
			sql = "DELETE FROM review WHERE r_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, r_num);
			pstmt2.executeUpdate();
			
            
			
			//예외 발생 없이 정상적으로 SQL문이 실행
			conn.commit();
		}catch(Exception e) {
			//예외 발생
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//예약 정보(리뷰번호로 조희)
		public ReservationVO getRevInfo(int r_num)
	            throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReviewVO review = null;
			ReservationVO rez = null;
			ProcedureVO proc = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
//				sql = "SELECT * FROM reservation v "
//				    + "LEFT JOIN procedure p ON p.m_num=v.m_num "
//				    + "LEFT JOIN review r ON v.rev_num=r.rev_num "
//				    + "WHERE v.m_num=?";
				
				sql = "SELECT * FROM reservation v "
					+ ", procedure p "
					+ "WHERE p.m_num=v.m_num "
					+ "AND v.m_num=?";			
				
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, r_num);
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//r_num rev_num m_num 정리
					rez = new ReservationVO();
					rez.setRev_date(rs.getString("rev_date"));
					rez.setRev_time(rs.getString("rev_time"));
					rez.setRev_num(rs.getInt("rev_num"));
					rez.setR_ox(rs.getString("r_ox"));
					rez.setM_num(rs.getInt("m_num"));
					rez.setP_num(rs.getInt("p_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
				}
			return rez;
			}

	
/* 예약DAO 사용
 

	//예약 정보
	public ReservationVO getRevInfo(int member_num)
            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO review = null;
		ReservationVO rez = null;
		ProcedureVO proc = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
//			sql = "SELECT * FROM reservation v "
//			    + "LEFT JOIN procedure p ON p.m_num=v.m_num "
//			    + "LEFT JOIN review r ON v.rev_num=r.rev_num "
//			    + "WHERE v.m_num=?";
			
			sql = "SELECT * FROM reservation v "
				+ ", procedure p "
				+ "WHERE p.m_num=v.m_num "
				+ "AND v.m_num=?";			
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, member_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//r_num rev_num m_num 정리
				rez = new ReservationVO();
				rez.setRev_date(rs.getString("rev_date"));
				rez.setRev_time(rs.getString("rev_time"));
				rez.setRev_num(rs.getInt("rev_num"));
				rez.setR_ox(rs.getString("r_ox"));
				rez.setM_num(rs.getInt("m_num"));
				rez.setP_num(rs.getInt("p_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
			}
		return rez;
		}

	 */
	
	//예약 개수
		public int getRezCount(int member_num)
				                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
//				sql = "SELECT COUNT(*) FROM comments c "
//					+ "WHERE c.r_num=?";
//				
				sql = "SELECT COUNT(*) "
					+ "FROM reservation v "
					+ "WHERE v.m_num=?";
				
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, member_num);
				//SQL문을 실행해서 결과행을 ResultSet 담음
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
	
		/*
	//예약 정보
	public List<ReservationVO> getRevInfo(int start,
            int end, int member_num)
            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationVO> list = null;
		ReservationVO rez = null;
		ProcedureVO proc = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
//			sql = "SELECT * FROM reservation v "
//			    + "LEFT JOIN procedure p ON p.m_num=v.m_num "
//			    + "LEFT JOIN review r ON v.rev_num=r.rev_num "
//			    + "WHERE v.m_num=?";
			
//			sql = "SELECT * FROM reservation v "
//				+ ", procedure p "
//				+ "WHERE p.m_num=v.m_num "
//				+ "AND v.m_num=?";			

//			sql = "SELECT *, rownum rnum "
//			+ "FROM reservation v, procedure p "
//			+ "WHERE p.m_num=v.m_num "
//			+ "AND v.m_num=? "
//			+ "AND rnum>=? AND rnum<=?";
			
			sql = "SELECT *"
				+ "FROM reservation v, procedure p "
				+ "WHERE p.m_num=v.m_num "
				+ "AND v.m_num=?";
            

			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, member_num);
//			pstmt.setInt(2, start);
//			pstmt.setInt(3, end);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<ReservationVO>();
			
			
			if(rs.next()) {
				//r_num rev_num m_num 정리
//				rez = new ReservationVO();
				rez.setRev_date(rs.getString("rev_date"));
				rez.setRev_time(rs.getString("rev_time"));
				rez.setRev_num(rs.getInt("rev_num"));
				rez.setM_num(rs.getInt("m_num"));
				rez.setP_num(rs.getInt("p_num"));
//				proc.setP_title(rs.getString("p_title"));
				
				list.add(rez);
				}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
			}
		return list;
		}
	
	*/
	
	
	/*
	//예약 목록 목록
	public List<Map<ReservationVO, ProcedureVO>> getRevInfo(int member_num)
			                		     throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<ReservationVO, ProcedureVO>> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성			
//			sql = "SELECT * FROM (SELECT *, rownum rnum "
//				+ "FROM comments c LEFT JOIN member m ON c.m_num = m.m_num "
//				+ "LEFT JOIN review r ON c.r_num = r.r_num WHERE "
//				+ "r.r_num=? ORDER BY c.c_num DESC) "
//				+ "WHERE rnum>=? AND rnum<=?";
			
			sql = "SELECT * FROM reservation v "
					+ ", procedure p "
					+ "WHERE p.m_num=v.m_num "
					+ "AND v.m_num=?";			

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, member_num);
			//SQL문을 실행해서 결과행들을 ResultSet에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<Map<ReservationVO, ProcedureVO>>();
			while(rs.next()) {
				ReservationVO rez = new ReservationVO();
				ProcedureVO proc = new ProcedureVO();
				rez.setRev_date(rs.getString("rev_date"));
				rez.setRev_time(rs.getString("rev_time"));
				rez.setRev_num(rs.getInt("rev_num"));
				rez.setM_num(rs.getInt("m_num"));
				rez.setP_num(rs.getInt("p_num"));
				proc.setP_title(rs.getString("p_title"));
				
				
				ReviewCommVO comm = new ReviewCommVO();
				MemberVO member = new MemberVO();
				comm.setC_num((Integer)rs.getInt("c_num"));
				comm.setC_content(rs.getString("c_content"));
				comm.setR_num((Integer)rs.getInt("r_num"));
				//이 값은 안 들어가나? 쿼리에서 빼서 없어도 될 듯
				comm.setM_num((Integer)rs.getInt("m_num"));
				
				list.add(comm);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	*/
	
	//댓글 등록
	public void insertCommReview(MemberVO member, ReviewVO review, ReviewCommVO comm)
			            throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO comments (c_num,"
					+ "c_date,c_content,m_num,r_num) "
					+ "VALUES (comments_seq.nextval,?,?,?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setDate(1, comm.getC_date());
			pstmt.setString(2, comm.getC_content());
			pstmt.setInt(3, member.getM_num());
			pstmt.setInt(4, review.getR_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	

		
	//댓글 개수
	public int getCommReviewCount(int r_num)
			                         throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
//			sql = "SELECT COUNT(*) FROM comments c "
//				+ "WHERE c.r_num=?";
//			
			sql = "SELECT COUNT(*) FROM comments c "
				+ "JOIN review r "
				+ "ON c.r_num=r.r_num "
				+ "WHERE r.r_num=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, r_num);
			//SQL문을 실행해서 결과행을 ResultSet 담음
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
	//댓글 목록
	public List<ReviewCommVO> getListCommReview(int start,
			                  int end, int r_num)
			                		     throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewCommVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성			
//			sql = "SELECT * FROM (SELECT *, rownum rnum "
//				+ "FROM comments c LEFT JOIN member m ON c.m_num = m.m_num "
//				+ "LEFT JOIN review r ON c.r_num = r.r_num WHERE "
//				+ "r.r_num=? ORDER BY c.c_num DESC) "
//				+ "WHERE rnum>=? AND rnum<=?";
			
			sql = "SELECT * "
				+ "FROM (SELECT c.*, rownum rnum "
				+ "FROM comments c LEFT JOIN member m ON c.m_num = m.m_num "
				+ "LEFT JOIN review r ON c.r_num = r.r_num "
				+ "WHERE c.r_num=? "
				+ "ORDER BY c.c_num DESC) "
				+ "WHERE rnum>=? AND rnum<=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, r_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문을 실행해서 결과행들을 ResultSet에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewCommVO>();
			while(rs.next()) {
				ReviewCommVO comm = new ReviewCommVO();
				comm.setC_num(rs.getInt("c_num"));
				comm.setC_date(rs.getDate("c_date"));
				comm.setC_content(StringUtil.useBrNoHtml(
						rs.getString("c_content")));
				comm.setR_num(rs.getInt("r_num"));
				comm.setM_num(rs.getInt("m_num"));
				
				list.add(comm);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//댓글 상세
		public ReviewCommVO getCommReview(int c_num)
		                                 throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReviewCommVO comm = null;
			String sql = null;

			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM comments WHERE c_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, c_num);
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs = pstmt.executeQuery();
				if(rs.next()) {
					comm = new ReviewCommVO();
					comm.setC_num(rs.getInt("c_num"));
					comm.setM_num(rs.getInt("m_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return comm;
		}
		
	//댓글 수정
		public void updateCommReview(ReviewCommVO comm)
		                                     throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE comments SET c_content=? "
					+ "WHERE c_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setString(1, comm.getC_content());
				pstmt.setInt(2, comm.getC_num());
		
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//댓글 삭제
		public void deleteCommReview(int c_num)
				                       throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM comments WHERE c_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, c_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
}
