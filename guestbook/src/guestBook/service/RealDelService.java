package guestBook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import guestBook.dao.GuestBookDao;
import guestBook.model.GuestBook;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class RealDelService {
	private GuestBookDao guestDao = new GuestBookDao();
	private static final int message_count_per_page = 3;
	private static int totalPage;
	
	public boolean delcheck(GuestBookRequest delReq) {
		boolean result=false;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

//			GuestBook guest = guestDao.selectById(conn, delReq.getId());
//			if (guest != null) {
//				JdbcUtil.rollback(conn);
//				throw new DuplicateIdException();
//			}
			
			System.out.println("service");
			
			result = guestDao.isExisted(conn, delReq.getId(), delReq.getPassword());
			if(result) {
			guestDao.delete(conn, new GuestBook(delReq.getId()));
			conn.commit();
			}
			System.out.println("왁");
			System.out.println(delReq.getId()+delReq.getPassword());
		} catch (NamingException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
}
	
	


