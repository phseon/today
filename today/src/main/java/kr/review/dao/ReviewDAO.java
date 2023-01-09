package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.reservation.vo.ReservationVO;
import kr.review.vo.ReviewVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class ReviewDAO {
	private static ReviewDAO instance = new ReviewDAO();
	
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	private ReviewDAO() {}
	
	//리뷰 등록
	public void insertReivew(ReviewVO review) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();

			//이후 별점, 예약 내용 추가
//			sql = "INSERT INTO review(r_num,r_date,"
//				+ "r_content,r_imgsrc,star,rev_num) VALUES ("
//				+ "review_seq.nextval(),SYSDATE,?,?,?,?)";
			sql = "INSERT INTO review(r_num,r_date,"
					+ "r_content,r_imgsrc) VALUES ("
					+ "review_seq.nextval,SYSDATE,?,?)";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, review.getR_content());
			pstmt.setString(2, review.getR_imgsrc());
//			pstmt.setInt(3, review.getStar());
//			pstmt.setInt(3, review.getRev_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
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
//					else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
//					else if(keyfield.equals("3")) sub_sql += "WHERE b.content LIKE ?";
				}
				
				//SQL문 작성(count와 zmember가 같이 수정되는게 좋으므로 조인 사용.)
//				sql = "SELECT COUNT(*) FROM review r JOIN member m USING(m_num) " + sub_sql;
				sql = "SELECT COUNT(*) FROM review" + sub_sql;
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
//				else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
//				else if(keyfield.equals("3")) sub_sql += "WHERE b.content LIKE ?";
			}
			
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
				+ "FROM	(SELECT * FROM review r "
				+ sub_sql
				+ "ORDER BY r.r_num DESC)a) "
				+ "WHERE rnum >= ? AND rnum <= ?";

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
//				review.setId(rs.getString("id"));
				
				list.add(review);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
			return list;
		}
	
	
	//글상세
	public ReviewVO getReview(int r_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO review = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM review r JOIN reservation v "
				+ "USING(rev_num) WHERE r.r_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, r_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review = new ReviewVO();
				review.setR_num(rs.getInt("r_num"));
				review.setR_content(rs.getString("r_content"));
				review.setR_date(rs.getDate("r_date"));
				review.setRev_num(rs.getInt("rev_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
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
//			if(board.getFilename()!=null) {
//				sub_sql += ",filename=?";
//			}
			
			sql = "UPDATE review SET r_content=? "
				+ sub_sql 
				+ "WHERE r_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, review.getR_content());
//			if(review.getFilename()!=null) {
//				pstmt.setString(++cnt, board.getFilename());
//			}
			pstmt.setInt(++cnt, review.getR_num());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//예약 정보
	public ReservationVO getRevInfo(int rev_num)
            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVO rez = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM reservation "
				+ "WHERE rev_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, rev_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//r_num rev_num m_num 정리
				rez = new ReservationVO();
				rez.setRev_num(rs.getInt("rev_num"));
				rez.setM_num(rs.getInt("m_num"));
//				rez.setRev_date(rs.getString("rev_date"));
//				rez.setRev_time(rs.getString("rev_time"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
			}
		return rez;
		}
	
	
	//예약현황
	/*
	public ReservationVO getReservation(int rev_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVO rez = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM reservation v JOIN member m "
				+ "USING(mem_num) JOIN member_detail d "
				+ "USING(mem_num) WHERE v.rev_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, rev_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rez = new ReservationVO();
				rez.setRev_num(rs.getInt("rev_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return rez;
	}
	*/
}
