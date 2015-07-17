/**
 * 
 */
package info.songjie365.jdbc.sample;

import java.sql.*;

/**
 * ResultSet Navigating
 * @author songjie
 *
 */
public class ResultSetNavigation {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/emp?useUnicode=true&characterEncoding=utf8";
	private static final String USER = "root";
	private static final String PASS = "123456";
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select id, age, first, last from employees order by id desc";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("moving cursor to the last record");
			rs.last();
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");
			printData(id, age, first, last);

			System.out.println("moving cursor to the first record");
			rs.first();
			id = rs.getInt("id");
			age = rs.getInt("age");
			first = rs.getString("first");
			last = rs.getString("last");
			printData(id, age, first, last);

			System.out.println("moving cursor to the nexr record");
			rs.next();
			id = rs.getInt("id");
			age = rs.getInt("age");
			first = rs.getString("first");
			last = rs.getString("last");
			printData(id, age, first, last);

			rs.close();
		}catch(Exception e){
			
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
				
		}
		
	}
	/**
	 *
	 * 打印数据
	 *
	 */
	private static void printData(int id, int age, String first, String last){
			System.out.println("id:" + id);
			System.out.println("age:" + age);
			System.out.println("first:" + first);
			System.out.println("last:" + last);
	}
		
}
