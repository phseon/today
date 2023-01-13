package kr.event.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
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
			
			//이벤트 남은 기간 나중에 처리
			sql = "INSERT INTO event (e_num, e_title, e_start, e_end, e_content, e_imgsrc, e_date, m_num, e_resbtn, e_thumb, cal_date) "
				+ "VALUES (event_seq.nextval, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, event.getE_title());
			pstmt.setString(2, event.getE_start());
			pstmt.setString(3, event.getE_end());
			pstmt.setString(4, event.getE_content());
			pstmt.setString(5, event.getE_imgsrc());
			pstmt.setInt(6, event.getM_num());
			pstmt.setString(7, event.getE_resbtn());
			pstmt.setString(8, event.getE_thumb());
			pstmt.setInt(9, calDay(event.getE_end()));
			
			
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
			
			sql = "SELECT COUNT(*) FROM event " + sub_sql;
			
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
				
				//남은 날짜 넣어주기
				event.setCal_date(calDay(rs.getString("e_end")));
				//System.out.println(rs.getString("e_end"));
				event.setE_imgsrc(rs.getString("e_imgsrc"));
				event.setE_thumb(rs.getString("e_thumb"));
				event.setE_resbtn(rs.getString("e_resbtn"));
				
				list.add(event);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//마감 임박 이벤트
	public List<EventVO> getCloseEvent(int eventNum) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EventVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT * FROM event WHERE cal_date >= 0 ORDER BY cal_date ASC) WHERE ROWNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eventNum);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<EventVO>();
			while(rs.next()) {
				EventVO event = new EventVO();
				event.setE_num(rs.getInt("e_num"));
				event.setE_title(StringUtil.useNoHtml(rs.getString("e_title")));
				event.setE_start(rs.getString("e_start"));
				event.setE_end(rs.getString("e_end"));
				event.setE_date(rs.getDate("e_date"));
				event.setCal_date(calDay(rs.getString("e_end")));
				
				System.out.println(calDay(rs.getString("e_end")));
				
				event.setE_imgsrc(rs.getString("e_imgsrc"));
				event.setE_thumb(rs.getString("e_thumb"));
				event.setE_resbtn(rs.getString("e_resbtn"));
				
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
				event.setE_thumb(rs.getString("e_thumb"));
				event.setE_resbtn(rs.getString("e_resbtn"));
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
			
			sql = "UPDATE event SET e_title = ?, e_start = ?, e_end = ?, e_thumb = ?, e_resbtn = ?, cal_date = ?" + sub_sql 
				+ ", e_content = ?, e_date = sysdate WHERE e_num = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, event.getE_title());
			pstmt.setString(++cnt, event.getE_start());
			pstmt.setString(++cnt, event.getE_end());
			pstmt.setString(++cnt, event.getE_thumb());
			pstmt.setString(++cnt, event.getE_resbtn());
			pstmt.setInt(++cnt, calDay(event.getE_end()));
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
	//cal_date 업데이트
	public void updateCalDate() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql = null;
		int i = 0;
		
		try {
			
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			//먼저 e_end(끝나는 날짜 가져와서 배열에 담기)
			sql = "SELECT e_end FROM event";
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			
			int rowcount = 0;
			
			if (rs.last()) {
			  rowcount = rs.getRow();
			  rs.beforeFirst();
			}

			String[] end_date = new String[rowcount];
			while(rs.next()) {
				end_date[i] = rs.getString("e_end");
				i++;
			}
			
			int[] update_cal = new int[rowcount];
			for(int j = 0; j < rowcount; j++) {
				update_cal[j] = calDay(end_date[j]);
			}
			
			//e_num 각 이벤트 글 번호 가져와서 배열에 담기
			sql = "SELECT e_num FROM event";
			pstmt2 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs2 = pstmt2.executeQuery(); 
			
			i = 0;
			
			int[] e_num = new int[rowcount];
			while(rs2.next()) {
				e_num[i] = rs2.getInt("e_num");
				i++;
			}
			
			//e_num마다 e_end 업데이트
			sql = "UPDATE event SET cal_date = ? WHERE e_num = ?";
			
			pstmt3 = conn.prepareStatement(sql);
			for(int j = 0; j < rowcount; j++) {
				pstmt3.setInt(1, update_cal[j]);
				pstmt3.setInt(2, e_num[j]);
				pstmt3.addBatch();
				
				if(j % 1000 == 0) {
					pstmt3.executeBatch();
				}
			}
			pstmt3.executeBatch();
			
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(rs2, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//글삭제
	public void deleteEvent(int e_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "DELETE FROM event WHERE e_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e_num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//파일 삭제
	public void deleteFile(int e_num, String e_imgType) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE event SET " + e_imgType + "= '' WHERE e_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//이벤트 남은 기간 계산
	//시작날짜도 받아와서 오늘날짜와 비교, 아직 시작날짜가 안됐으면 진행전 중이면 중 지났으면 마감 중일 땐 d-day표시
	public int calDay(String endDate) {
		Calendar cal = Calendar.getInstance();
		String[] split_endDate = endDate.split("-");
		cal.set(Integer.parseInt(split_endDate[0]), Integer.parseInt(split_endDate[1]) - 1, Integer.parseInt(split_endDate[2]));

		long d_day = cal.getTimeInMillis();
		
		long today = System.currentTimeMillis();
		long result = d_day - today;
		
		long remain = result / 1000 / 60 / 60 / 24;
		
		int remainDay;
		
		remainDay = Long.valueOf(remain).intValue();
		
		return remainDay;
	}
}
