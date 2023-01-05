package kr.myinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MyInfoDAO {
	
	private static MyInfoDAO instance = new MyInfoDAO();
	public static MyInfoDAO getInstance() {
		return instance;
	}
	private MyInfoDAO() {}

	
	// 회원탈퇴
	public void deleteMemberInfo(int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); //auto commit 해제. sql 2개이상 실핼할 땐 commit해제
			sql = "delete from member_detail where m_num = ?"; // member_detail에서 정보삭제
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, member_num);
			pstmt1.executeUpdate();
			
			sql = "update member set auth = 0 where m_num = ?"; // 권한 0으로 수정
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, member_num);
			pstmt2.executeUpdate();

			conn.commit(); // 모두 실행 성공시
			
		}catch(Exception e) {
			conn.rollback(); // 하나라도 실패시
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
	
	// 회원정보 조회
	public MemberVO getMemberInfo(int member_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
			
		try {
			conn = DBUtil.getConnection();
			// mypage에서 이름, 전화번호, 이메일, 우편번호, 주소 사용
			sql = "select * from member m join member_detail d on m.m_num = d.m_num where m.m_num = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			rs = pstmt.executeQuery();
			if(rs.next()) { //출력되는 정보가 있으면 각 vo에 담기
				member = new MemberVO();
				member.setM_num(member_num);
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setName(rs.getString("name"));
				member.setPwd(rs.getString("pwd"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setImgsrc(rs.getString("imgsrc"));
				member.setContent(rs.getString("content"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
		
	// 회원정보 수정
	public void updateMemberInfo(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "update member_detail set name=?, email=?, phone=?, zipcode=?, address1=?, address2=? where m_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setInt(7, member.getM_num());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 비밀번호 수정
	public void updateMemberPassword(String password, int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "update member_detail set pwd=? where m_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setString(1, password);//새비밀번호
			pstmt.setInt(2, member_num);//회원번호
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 리뷰 조회
	
	// qna 조회
	
	// 병원 예약 조회
	
}
