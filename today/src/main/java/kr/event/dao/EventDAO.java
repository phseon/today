package kr.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.event.vo.EventVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class EventDAO {
	private static EventDAO instance = new EventDAO();
	
	public static EventDAO getInstance() {
		return instance;
	}
	private EventDAO() {}
	
	//이벤트 게시글 등록
	public void insertEvent(EventVO event) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO event (e_num, e_title, e_start, e_end, e_content, e_imgsrc, e_date, m_num) "
				+ "VALUES (event_seq.nextval, ?, ?, ?, ?, ?, SYSDATE, ?)";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, event.getE_title());
			pstmt.setString(2, event.getE_start());
			pstmt.setString(3, event.getE_end());
			pstmt.setString(4, event.getE_content());
			pstmt.setString(5, event.getE_imgsrc());
			pstmt.setInt(6, event.getM_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//이벤트 검색 레코드 수
	public int getEventCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				//검색글 개수
				if(keyfield.equals("1")) sub_sql += "WHERE e_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE e_content LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM event" + sub_sql;
			
			pstmt = conn.prepareStatement(sql);
			
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//이벤트 목록
	public List<EventVO> getListEvent(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EventVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				//검색글 개수
				if(keyfield.equals("1")) sub_sql += "WHERE e_title LIKE ? ";
				else if(keyfield.equals("2")) sub_sql += "WHERE e_content LIKE ? ";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
				+ "FROM (SELECT * FROM event "
				+ sub_sql
				+ "ORDER BY e_num DESC)a) "
				+ "WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<EventVO>();
			while(rs.next()) {
				EventVO event = new EventVO();
				event.setE_num(rs.getInt("e_num"));
				event.setE_title(StringUtil.useNoHtml(rs.getString("e_title")));
				event.setE_start(rs.getString("e_start"));
				event.setE_end(rs.getString("e_end"));
				event.setE_date(rs.getDate("e_date"));
				//나중에 썸네일 열 추가해서 불러오기
				event.setE_imgsrc(rs.getString("e_imgsrc"));
				
				list.add(event);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//이벤트 글상세
	public EventVO getEvent(int e_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EventVO event = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM event WHERE e_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				event = new EventVO();
				event.setE_num(rs.getInt("e_num"));
				event.setE_title(rs.getString("e_title"));
				event.setE_start(rs.getString("e_start"));
				event.setE_end(rs.getString("e_end"));
				event.setE_imgsrc(rs.getString("e_imgsrc"));
				event.setE_content(rs.getString("e_content"));
				event.setE_date(rs.getDate("e_date"));
				event.setM_num(rs.getInt("m_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return event;
	}
	//글수정
	public void updateEvent(EventVO event) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			//이미지 파일 체크
			if(event.getE_imgsrc() != null) {
				sub_sql += ", e_imgsrc = ?";
			}
			
			sql = "UPDATE event SET e_title = ?, e_start = ?, e_end = ?" + sub_sql 
				+ ", e_content = ?, e_date = sysdate WHERE e_num = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, event.getE_title());
			pstmt.setString(++cnt, event.getE_start());
			pstmt.setString(++cnt, event.getE_end());
			if(event.getE_imgsrc() != null) {
				pstmt.setString(++cnt, event.getE_imgsrc());
			}
			pstmt.setString(++cnt, event.getE_content());
			pstmt.setInt(++cnt, event.getE_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
