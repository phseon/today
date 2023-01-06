package kr.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import kr.information.vo.InformationVO;
import kr.util.DBUtil;

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
	
	//글목록
	//public List<InformationVO> getListInfo(in)
	
	//글상세
	
	//글수정
	
	
	//글삭제
}
