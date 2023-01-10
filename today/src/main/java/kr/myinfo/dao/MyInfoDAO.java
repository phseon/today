package kr.myinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.doctor.vo.DoctorVO;
import kr.member.vo.MemberVO;
import kr.reservation.vo.ReservationVO;
import kr.review.vo.ReviewVO;
import kr.util.DBUtil;

public class MyInfoDAO {
	
	private static MyInfoDAO instance = new MyInfoDAO();
	public static MyInfoDAO getInstance() {
		return instance;
	}
	private MyInfoDAO() {}

	// 로그인한 아이디 체크
	public MemberVO checkMemberExist(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			// 동일한 id의 정보가 있는지 조회
			sql = "SELECT * FROM member m left outer join member_detail d ON m.m_num=d.m_num WHERE m.id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setM_num(rs.getInt("m_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPwd(rs.getString("pwd"));
				member.setImgsrc(rs.getString("imgsrc")); // mypage photo에서 사용
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	
	// 회원탈퇴
	public void deleteMemberInfo(int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); //auto commit 해제. sql 2개이상 실핼할 땐 commit해제
			
			sql = "update member set auth=0 where m_num=?"; // 권한 0으로 수정
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, member_num);
			pstmt1.executeUpdate();
			
			sql = "delete from member_detail where m_num=?"; // member_detail에서 정보삭제
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
	public void updateMemberPassword(String passwd, int member_num)throws Exception{
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
			pstmt.setString(1, passwd);//새비밀번호
			pstmt.setInt(2, member_num);//회원번호
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 프로필 사진 수정
	public void updateMyPhoto(String photo, int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "update member_detail set imgsrc=? where m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, photo);
			pstmt.setInt(2, member_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 프로필 사진 삭제
	public void deleteMyPhoto(int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "update member_detail set imgsrc=null where m_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 리뷰 조회
	public ReviewVO getReviewInfo(int member_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO myReview = null;
		String sql = null;
			
		try {
			conn = DBUtil.getConnection();
			// 내가 작성한 리뷰보기 페이지에서 작성날짜, 리뷰내용 확인
			sql = "select * from reservation res join review rev on res.rev_num = rev.rev_num where res.m_num = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			rs = pstmt.executeQuery();
			if(rs.next()) { //출력되는 정보가 있으면 각 vo에 담기. 작성날짜, 리뷰내용, 멤버번호만 넘기기
				myReview = new ReviewVO();
				// review에 m_num이 없어서 member_num 을 저장못함. 임의의변수에 저장해보기 -> 불가능. 그냥 넘기지말아보자
				myReview.setR_date(rs.getDate("r_date"));
				myReview.setR_content(rs.getString("r_content"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return myReview;
	}
	
	// 병원 예약 조회 (예약날짜 예약시간 병원의사 시술이름)
	public ReservationVO getReservationInfo(int member_num)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVO myReservation = null;
		String sql = null;
			
		try {
			conn = DBUtil.getConnection();
			// 내가 예약한 병원진료 보기에서 날짜, 시간, 진료과목 보기
			sql = "select * from reservation where m_num=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			rs = pstmt.executeQuery();
			if(rs.next()) { //출력되는 정보가 있으면 각 vo에 담기. 예약날짜, 예약시간, 내 번호 넘기기.
				myReservation = new ReservationVO();
				//myReservation.setM_num(rs.getInt(member_num)); // 이건 의사에대한 이름
				myReservation.setRev_date(rs.getString("rev_date"));
				myReservation.setRev_time(rs.getString("rev_time")); 
				myReservation.setP_num(rs.getInt("p_num")); 
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return myReservation;
	}
	
	
	// 시술정보 조회 (병원예약정보에서 넘긴 p_num에 대한 정보를 뽑아내기위해)
	public DoctorVO getProcedureInfo(int p_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DoctorVO myProcedure = null;
		String sql = null;
			
		try {
			conn = DBUtil.getConnection();
			// 모든정보 들고오기
			sql = "select * from procedure where p_num = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();
			if(rs.next()) { //출력되는 정보가 있으면 각 vo에 담기. 예약날짜, 예약시간, 진료과목 넘기기.
				myProcedure = new DoctorVO();
				myProcedure.setP_title(rs.getString("p_title"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return myProcedure;
		}
	

	
}
