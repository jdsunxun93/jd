package JavaBean;
import java.sql.*;

import com.mysql.jdbc.CallableStatement;

public class DBConnection {
	
	/**
	 * 驱动程序名
	 */
	String driverName ="com.mysql.jdbc.Driver";
	
	/**
	 * 数据库用户名
	 */
	String userName ="root";
	
	/**
	 * 密码
	 */
	String userPasswd ="rootpwd";
	
	/**
	 * 数据库名
	 */
	String dbName ="klyc";
	
	/**
	 * 表名
	 */
	String tableName ="goodspara";
	
	/**
	 * 联结字符串
	 */
	String url ="jdbc:mysql://localhost:3306/";//create user 'so'@'10.2.15.31' identified by '123456';
	
	/**
	 * 链接设置
	 */
	String url2 = "?autoReconnect=true&useUnicode=true&characterEncoding=gbk&mysqlEncoding=gbk";
	   
	/**
	 * 链接时，使用的变量
	 */
	Connection con =null;
	
	/**
	 * 链接时，使用的变量
	 */
	Statement stmt =null;
	
	/**
	 * 链接时，使用的变量
	 */
	ResultSet rs =null;
	
	/**
	 * 链接时，使用的变量
	 */
	ResultSetMetaData rmeta =null;
	
	/**
	 * 链接时，使用的变量
	 */
	CallableStatement cstmt=null;
	
	
	/**
	 * 连接数据库的方法
	 */
	public boolean connect(){
		//注意database的编码使用的是utf8，不然会插入中文时，出现乱码的情况
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
	
    //执行Select语句并返回结果集
	public ResultSet executeQuery(String sql) throws SQLException
	{
		ResultSet rs = null;
		if(con != null && stmt != null)
			rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	//执行更新语句，如果执行成功，则返回影响的行数，0为未影响任何一行，如果不成功，则返回-1
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
