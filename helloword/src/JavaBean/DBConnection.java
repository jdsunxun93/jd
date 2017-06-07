package JavaBean;
import java.sql.*;

import com.mysql.jdbc.CallableStatement;

public class DBConnection {
	
	/**
	 * ����������
	 */
	String driverName ="com.mysql.jdbc.Driver";
	
	/**
	 * ���ݿ��û���
	 */
	String userName ="root";
	
	/**
	 * ����
	 */
	String userPasswd ="rootpwd";
	
	/**
	 * ���ݿ���
	 */
	String dbName ="klyc";
	
	/**
	 * ����
	 */
	String tableName ="goodspara";
	
	/**
	 * �����ַ���
	 */
	String url ="jdbc:mysql://localhost:3306/";//create user 'so'@'10.2.15.31' identified by '123456';
	
	/**
	 * ��������
	 */
	String url2 = "?autoReconnect=true&useUnicode=true&characterEncoding=gbk&mysqlEncoding=gbk";
	   
	/**
	 * ����ʱ��ʹ�õı���
	 */
	Connection con =null;
	
	/**
	 * ����ʱ��ʹ�õı���
	 */
	Statement stmt =null;
	
	/**
	 * ����ʱ��ʹ�õı���
	 */
	ResultSet rs =null;
	
	/**
	 * ����ʱ��ʹ�õı���
	 */
	ResultSetMetaData rmeta =null;
	
	/**
	 * ����ʱ��ʹ�õı���
	 */
	CallableStatement cstmt=null;
	
	
	/**
	 * �������ݿ�ķ���
	 */
	public boolean connect(){
		//ע��database�ı���ʹ�õ���utf8����Ȼ���������ʱ��������������
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    con = DriverManager.getConnection( url + dbName+url2, userName, userPasswd );
		    stmt = con.createStatement();
		   // System.out.println("JDBC Connect:"+Pub.df.format(new Date()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean isConnect()
	{
		return con != null && stmt != null;
	}
	
    //ִ��Select��䲢���ؽ����
	public ResultSet executeQuery(String sql) throws SQLException
	{
		ResultSet rs = null;
		if(con != null && stmt != null)
			rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	//ִ�и�����䣬���ִ�гɹ����򷵻�Ӱ���������0ΪδӰ���κ�һ�У�������ɹ����򷵻�-1
	public int executeUpdate(String sql) throws SQLException
	{
		return (con != null && stmt != null) ? stmt.executeUpdate(sql) : -1;
	}
	
	public void close()
	{
		try{
			
			if(stmt!=null && !stmt.isClosed())
				stmt.close();
			
			if(con != null && !con.isClosed())
				con.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void finalize()
	{
		try {		
			if(stmt != null )
				stmt.close();
		
			if(con != null)
				con.close();
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
