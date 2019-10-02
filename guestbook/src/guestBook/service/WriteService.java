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

public class WriteService {
	private GuestBookDao guestDao = new GuestBookDao();
	private static final int message_count_per_page = 3;
	private static int totalPage;
	
	public void write(GuestBookRequest writeReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

//			GuestBook guest = guestDao.selectById(conn, writeReq.getId());
//			if (guest != null) {
//				JdbcUtil.rollback(conn);
//				throw new DuplicateIdException();
//			}
			
			System.out.println("service");
			
			guestDao.insert(conn,
					new GuestBook(writeReq.getId(), writeReq.getName(), writeReq.getPassword(), writeReq.getMessage()));
			conn.commit();
			System.out.println( writeReq.getName());
		} catch (NamingException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
	
	


