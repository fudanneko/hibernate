package core.dao;

import core.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface CoreDao<P, I> {
	int insert(P pojo);

	int deleteById(I id);

	int update(P pojo);

	P selectById(I id);

	List<P> selectAll();
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
    }

//	============JDBC寫法=========================
//	static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
//	static final String USERNAME = "root";
//	static final String PASSWORD = "aa1234";
//
//	public static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//	}
}
