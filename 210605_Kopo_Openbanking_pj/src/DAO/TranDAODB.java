package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Util.ConnectionFactory;
import Util.JDBCClose;
import VO.TranVO;
import VO.UserVO;

public class TranDAODB {
	
	
	
	/**
	 * 거래로그 저장
	 * @param vo
	 * @throws Exception
	 */
	public void InputTransLog(TranVO vo) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tranhistory(fromacc, frombank, tr_code, transeq, balance, tosacc, tobank) ");
			sql.append("values(?, ?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getFromacc());
			pstmt.setString(2, vo.getFrombank());
			pstmt.setString(3, vo.getTr_code());
			pstmt.setInt(4, vo.getTranseq());
			pstmt.setInt(5, vo.getBalance());
			pstmt.setString(6, vo.getToacc());
			pstmt.setString(7, vo.getTobank());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	/**
	 * 계좌번호에 따른 전체내역리턴
	 * @param uv
	 * @return
	 * @throws Exception
	 */
	public List<TranVO> getAllTransLog(UserVO uv) throws Exception{
		List<TranVO> tvolst = new ArrayList<>();
		//TranVO tvo = new TranVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select fromacc, frombank, tran_dt, tr_code, transeq, balance, tosacc, tobank ");
			sql.append("from tranhistory ");
			sql.append("where fromacc = ? ");
			sql.append("order by tran_dt ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, uv.getAccount());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String fromacc = rs.getString("fromacc");
				String frombank = rs.getString("frombank");
				Date tran_dt = rs.getDate("tran_dt");
				String tr_code = rs.getString("tr_code");
				int transeq = rs.getInt("transeq");
				int balance= rs.getInt("balance");
				String tosacc = rs.getString("tosacc");
				String tobank = rs.getString("tobank");
				TranVO tvo = new TranVO(fromacc, frombank, tran_dt, tr_code, transeq, balance, tosacc, tobank);
				tvolst.add(tvo);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return tvolst;
	}

}
