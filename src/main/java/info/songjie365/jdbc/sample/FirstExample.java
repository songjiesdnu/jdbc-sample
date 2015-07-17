/**
 * 
 */
package info.songjie365.jdbc.sample;

//第一步：引入相关的包
import java.sql.*;

/**
 * @author songjie
 *
 */
public class FirstExample {
	//jdbc driver和database url
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_RUL = "jdbc:mysql://localhost:3306/emp?useUnicode=true&characterEncoding=utf8";
	
	//账号
	private static final String USER = "root";
	private static final String PASS = "123456";
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//第二步：注册jdbc driver
			Class.forName(JDBC_DRIVER);
			
			//第三步：打开connection
			conn = DriverManager.getConnection(DB_RUL, USER, PASS);
			
			//第四步：执行查询
			stmt = conn.createStatement();
			String sql = "select id,age,first,last from employees";
			ResultSet rs = stmt.executeQuery(sql);
			
			//第五步：提取数据
			while(rs.next()){
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				System.out.println("id:" + id);
				System.out.println("age:" + age);
				System.out.println("first:" + first);
				System.out.println("last:" + last);
				System.out.println("***************");
			}
			
			//第六步：清理执行环境，以释放资源
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
