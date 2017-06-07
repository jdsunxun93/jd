package JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBWorker {

	DBConnection db=new DBConnection();
	
	/*
	 * �������ݿ��е��û�������
	 */
	public ArrayList<String> getUsernames(){
		
		ResultSet rs=null;
		ArrayList<String> usernames=null;
		try {
			
			String sql="select username from userInfo";
			rs=db.executeQuery(sql);
			usernames=new ArrayList<String>();
			while(rs.next()){
				usernames.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				} 				
				if(db.isConnect()){
					db.close();
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usernames;
	}
	
	/*
	 * �����û�������������
	 */
	public String getPassword(String username){
		
		ResultSet rs=null;
		String password = null;
		try {			
			String sql="select password from userInfo where username='"+username+"'";			
			rs=db.executeQuery(sql);
			while(rs.next()){
				password=rs.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				} 				
				if(db.isConnect()){
					db.close();
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return password;
	}
	
	/*
	 * �����ݿ��в����û���Ϣ
	 */
	public Boolean insertUserInfo(String username,String password,String email){
		ResultSet rs=null;
		Boolean a=true;
		try {
			String sql="insert into userInfo values (?,?,?)";
			db.executeUpdate(sql);
			//pst.setString(1, username);
			//pst.setString(2, password);
			//pst.setString(3, email);
			//pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a=false;
		}finally{
			try {
				if(rs!=null){
					rs.close();
				} 				
				if(db.isConnect()){
					db.close();
				}
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		return a;
	}
	
	/*
	 * ����goodsid�����ݿ��з���GoodsItem
	 */
	public GoodsItem getGoodsItem(String goodsid){
		ResultSet rs=null;
		GoodsInfo goods=null;
		try {
	        db.connect();
			String sql="select Goods_ID,Goods_name,price,salenumber,describe from goodsPara where Goods_ID="+goodsid;
			
			rs = db.executeQuery(sql);		
			goods = new GoodsInfo();
			while(rs.next()) { 
				goods.setGoods_ID(rs.getString("Goods_ID"));
				goods.setGoods_name(rs.getString("Goods_name"));
				goods.setPrice(rs.getFloat("price"));
				goods.setSalenumber(rs.getInt("salenumber"));
				goods.setDescribe(rs.getString("describe"));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			//�ر����ݿ�����
			try {
				if(rs!=null){
					rs.close();
				} 				
				if(db.isConnect()){
					db.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
		GoodsItem goodsitem=new GoodsItem();
		//��ѡ�����Ʒ��ӵ�items��
		goodsitem.setGoods(goods);
		goodsitem.setCount(1);
		goodsitem.setTotalprice(goodsitem.getCount()*goodsitem.getGoods().getPrice());
		return goodsitem;
	}	
}