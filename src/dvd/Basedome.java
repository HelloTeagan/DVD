package dvd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Basedome {
	//��������
		static Connection conn;
		static Statement Statement;
		static ResultSet rs;
		static int count;
		static PreparedStatement psts;
		
		public static Connection getConn() {
			//1��������
			try {
				// �������ݿ�����  com.mysql.jdbc.Driver
			       String driver = "com.mysql.cj.jdbc.Driver";
			        // ��ȡmysql���ӵ�ַ
			       String url = "jdbc:mysql://localhost:3306/test?&useSSL=false&serverTimezone=UTC";
			        // ��������
			       String username = "root";
			        // ���ݿ�����
			        String password = "199825xmk";
			        // ��ȡһ�����ݵ�����
			        // ��ȡ���ӵ�һ��״̬	       
		            Class.forName(driver);
		            //getConnection()����������MySQL���ݿ⣡
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
		//2.��ѯ
		public static ResultSet executeQurry(String sql,Object[] args) {
			//���Ӷ���
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
		//3.��ɾ��
		public static int executeUpdata(String sql,Object[] args) {
			//���Ӷ���
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
