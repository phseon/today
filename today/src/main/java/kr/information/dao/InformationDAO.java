package kr.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.information.vo.InformationVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class InformationDAO {
	private static InformationDAO instance = new InformationDAO();
	
	public static InformationDAO getInstance() {
		return instance;
	}
	private InformationDAO() {}
	
	//글등록
	public void insertInformation(InformationVO info) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "insert into information(info_num,info_title,info_content,m_num) values(information_seq.nextval,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getInfo_title());
			pstmt.setString(2, info.getInfo_content());
			pstmt.setInt(3, info.getM_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글의 총 개수
	public int getCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select count(*) from information";
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
	public List<InformationVO> getListInfo(int startRow, int endRow)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<InformationVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * from (select a.* ,rownum rnum from (select * from information b join member m using(m_num) order by b.info_num desc)a) where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<InformationVO>();
			while(rs.next()) {
				InformationVO informationVO = new InformationVO();
				informationVO.setInfo_num(rs.getInt("info_num"));
				informationVO.setInfo_title(StringUtil.useNoHtml(rs.getString("info_title")));
				informationVO.setId(rs.getString("id"));
				informationVO.setInfo_date(rs.getDate("info_date"));
				
				list.add(informationVO);
				
			}
				
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
		
		
	}
	
	//글상세
	public InformationVO getinfodetail(int info_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InformationVO info = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * from information i join member m using(m_num) join member_detail d using(m_num) where i.info_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, info_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				info = new InformationVO();
				info.setInfo_num(rs.getInt("info_num"));
				info.setInfo_title(rs.getString("info_title"));
				info.setInfo_content(rs.getString("info_content"));
				info.setInfo_date(rs.getDate("info_date"));
				info.setId(rs.getString("id"));
				info.setM_num(rs.getInt("m_num"));
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return info;
	}
	
	
	
	
	
	//글수정
	
	
	
	//글삭제
}
