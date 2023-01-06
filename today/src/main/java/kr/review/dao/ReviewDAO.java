package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.review.vo.ReviewVO;
import kr.util.DBUtil;

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
			
			sql = "INSERT INTO review(r_num,r_date,"
				+ "r_content,r_imgsrc,star,rev_num) VALUES ("
				+ "review_seq.nextval(),SYSDATE,?,?,?,?)";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, review.getR_content());
			pstmt.setString(2, review.getR_imgsrc());
			pstmt.setInt(3, review.getStar());
			pstmt.setInt(4, review.getRev_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
