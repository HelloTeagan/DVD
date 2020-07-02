package dvd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Basedome {
	//声明属性
		static Connection conn;
		static Statement Statement;
		static ResultSet rs;
		static int count;
		static PreparedStatement psts;
		
		public static Connection getConn() {
			//1加载驱动
			try {
				// 加载数据库驱动  com.mysql.jdbc.Driver
			       String driver = "com.mysql.cj.jdbc.Driver";
			        // 获取mysql连接地址
			       String url = "jdbc:mysql://localhost:3306/test?&useSSL=false&serverTimezone=UTC";
			        // 数据名称
			       String username = "root";
			        // 数据库密码
			        String password = "199825xmk";
			        // 获取一个数据的连接
			        // 获取连接的一个状态	       
		            Class.forName(driver);
		            //getConnection()方法，连接MySQL数据库！
		            conn=DriverManager.getConnection(url,username,password);
		            return conn;
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		//2.查询
		public static ResultSet executeQurry(String sql,Object[] args) {
			//连接对象
			getConn();
			try {
				
				psts=conn.prepareStatement(sql);
				for(int i=0;i<args.length;i++) {
					psts.setObject(i+1,args[i]);
				}
				rs = psts.executeQuery();
				return rs;
			}catch(SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}
		//3.增删改
		public static int executeUpdata(String sql,Object[] args) {
			//连接对象
			getConn();
			try {			
				psts=conn.prepareStatement(sql);
				for(int i=0;i<args.length;i++) {
					psts.setObject(i+1,args[i]);
				}
				count = psts.executeUpdate();
				return count;
			}catch(SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
		}
		public static void closeResource() {
			if(rs!=null) {
				try {
					rs.close();
					if(psts!=null) {
						psts.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

}
