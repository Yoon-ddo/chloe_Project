package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.ConnectionFactory;
import Util.JDBCClose;
import VO.LogVO;

public class AdminDAODB {
	
	
	
	/**
	 * 전체유저뽑기. 메뉴 1:전체, 2:id검색, 3:이름검색, 4:전화번호검색
	 * @param i
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<LogVO> showUser(int i, String str) throws Exception{
		
		List<LogVO> allst = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, res_code, gender, phone ");
			sql.append("from userinfo ");
			
			switch(i) {
			case 1:
				sql.append("where id not like 'admin' ");
				pstmt = conn.prepareStatement(sql.toString());
				break;
			case 2:
				sql.append("where id = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, str);
				break;
				
			case 3:
				sql.append("where name = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, str);
				break;
			case 4:
				sql.append("where phone = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, str);
				break;
				
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String res_code = rs.getString("res_code");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				
				LogVO lg = new LogVO();
				lg.setId(id);
				lg.setName(name);
				lg.setRes_code(res_code);
				lg.setGender(gender);
				lg.setPhone(phone);
				
				allst.add(lg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		return allst;
		
	}

	

}
