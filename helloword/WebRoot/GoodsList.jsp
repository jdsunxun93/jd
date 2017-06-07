<%@page import="JavaBean.GoodsItem"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="JavaBean.DBConnection" %>
<%
	//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">
    <%//=basePath%>
    
    <title>My git JSP 'GoodsList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  	String sessionsize=null;
    ArrayList<String> sessionkind=null;
    String selsize=request.getParameter("size");
  		String[] selkind=request.getParameterValues("kind");
  		ArrayList<String> kindlist=new ArrayList<String>();
  		if(selsize!=null){
  		session.setAttribute("size", selsize);
  		}
  		if(selkind!=null){
  	for(int i=0;i<selkind.length;i++){
  			kindlist.add(selkind[i]);
  			session.setAttribute("kind",kindlist);
  	}
  		}
  		sessionsize=(String)session.getAttribute("size");
  		sessionkind=(ArrayList<String>)session.getAttribute("kind");
  %>
   <!-- 选择此时到底用户有没有登录，即是否是从Login.jsp页面中跳转进来的-->
   <%
   	String username=(String)session.getAttribute("username");
     
      if(username!=null){
      ArrayList<GoodsItem> goodsitem=(ArrayList<GoodsItem>)session.getAttribute(username);
      int count=0;
      if(goodsitem==null){
         count=0;
      }
      else{
         count=goodsitem.size();
      }
   %>
   <p><%=username%>,欢迎您  <a href="servlet/DeLoginServlet">注销</a>    您现在购物车中有<%= count %>件商品
   <%
   	}
      else{
   %>
    <a href="Login.jsp?goodsid=-1">登录</a>    <a href="Register.jsp">注册</a>
    <%
    	}
    %>
    
   <br>
   <br>
   <br>
    <p>欢迎选购商品</p>
    <!-- 选择表单 -->
    <form method="post" action="GoodsList.jsp">
    <p>商品分类</p>
    <table>
    <%
    	if(sessionsize==null){
    %>
    <tr>
    <td>大小</td>    <td><input type="radio" name="size" value="big" >60cm以上      <input type="radio" name="size" value="small" >60cm以下</td>
    </tr>
    <%
    	} 
        if(sessionkind==null){
    %>
    <tr>
    <td>种类</td>    <td><input type="checkbox" name="kind" value="bear">小熊
				<input type="checkbox" name="kind" value="dog">小狗
				<input type="checkbox" name="kind" value="cat">猫咪
				    </td>
    </tr>
    <%
    	}
    %>
    </table>
		<%
			if(sessionsize==null||sessionkind==null){
		%>
		    <INPUT name="SUBMIT" type="submit" value="submit">
		<%
			}
		%>
		
		<p>删除您选择的商品种类：
		<%
					if(sessionsize!=null){
				%>
		商品大小 
		  <%
					if(sessionsize.equals("big")){
				%>
		  <a href="servlet/DeleteSelectServlet?name=big">60cm以上</a>
		  <%
		  	}
		  		  if(sessionsize.equals("small")){
		  %>
		  <a href="servlet/DeleteSelectServlet?name=small">60cm以下</a>
		<%
			} 
				}
			
				if(sessionkind!=null){
		%>
		商品类型 
		<%
			for(int i=0;i<sessionkind.size();i++){
			if(sessionkind.get(i).equals("bear")){
		%>
			<a href="servlet/DeleteSelectServlet?name=bear">小熊</a>
			<%
				}
				if(sessionkind.get(i).equals("dog")){
			%>
			<a href="servlet/DeleteSelectServlet?name=dog">小狗</a>
			<%
				}
				if(sessionkind.get(i).equals("cat")){
			%>
			<a href="servlet/DeleteSelectServlet?name=cat">猫咪</a>
			<%
				}
					}
					}
			%>
		
</p>
<br>
<br>
<br>
    <p>商品排序</p>
    <a href="GoodsList.jsp?name2=price">价格</a>  <a href="GoodsList.jsp?name2=postprice">是否包邮</a>   <a href="GoodsList.jsp?name2=salenumber">销量</a>
    </form>

    <table border=1>
       <tr>
       <td>商品ID</td>
       <td>名称</td>
       <td>价格</td>
       <td>销量</td>
       <td>描述（大小、种类、邮费）</td>
       <td>操作</td>
       </tr>

    <%
    	//控制商品排序
        String getname=null;
        getname=request.getParameter("name2");

          DBConnection db=new DBConnection();
          db.connect();
           
           String sql="select Goods_ID,Goods_name,price,salenumber,size,kind,postprice from goodsPara where 1=1";
           if(sessionsize!=null){
           sql=sql+" and size='"+sessionsize+"'";
           }
           if(sessionkind!=null){
           sql=sql+" and kind='"+sessionkind.get(0)+"'";
           	for(int i=1;i<sessionkind.size();i++){
           	sql=sql+" or kind='"+sessionkind.get(i)+"'";
           	}
           }
           if(getname!=null){
           		sql=sql+" order by "+getname;
           }
           //db.exectueQuery(sql);
           ResultSet rs=db.executeQuery(sql);
           while(rs.next()){
           //这个地方rs只能取一次值
           String goodsid=rs.getString("Goods_ID");
           String goodsname=rs.getString("Goods_name");
           Float price=rs.getFloat("price");
           Integer salenumber=rs.getInt("salenumber");
           String size=rs.getString("size");
           String kind=rs.getString("kind");
           Float postprice=rs.getFloat("postprice");
           String post=null;
    	      if(postprice==0){
    	      post="包邮";
    	      }
    	      else{
    	      post=Float.toString(postprice)+"元邮费";
    	      }
    %>
       <tr>
       <td><%=goodsid%></td>
       <td><%=goodsname%></td>
       <td><%=price%></td>
       <td><%=salenumber%></td>
       <td><%=size%>、<%=kind%>、<%=post%></td>
       <%
       //当用户已登录时
       	if(username!=null){
              //从登录页面获取的选择的商品的商品号和选择类型
              
                         ArrayList<GoodsItem> shopcart=(ArrayList<GoodsItem>)session.getAttribute(username);
                  ArrayList<String> goodsids=new ArrayList<String>();
             if(shopcart!=null){
                   //shopcart.size()=2;且购物车中的两个条目是一样的
       	            for(int i=0;i<shopcart.size();i++){
       	              GoodsItem goodsitems=shopcart.get(i);
       	              goodsids.add(goodsitems.getGoods().getGoods_ID());
       	            }
       	          
                     if(goodsids.contains(goodsid)){
       %>
              		<td><p>已购买</p></td>
		              <%
		              }
		              else{
		              %>
		              <!-- 如果这个地方在servlet前面添加斜杠，则退回到localhost:8080的根目录，如果不添加，则表示在当前目录下 -->
				         <td><a href="servlet/BuyServlet?goodsid=<%=goodsid %>&name=pay">购买</a>
				       /<a href="servlet/BuyServlet?goodsid=<%=goodsid %>&name=add">添加到购物车</a></td>      
		              <% 
		              }
              }
              //购物车为空时，没有已购买
       		else{
        %>
         <td><a href="servlet/BuyServlet?goodsid=<%=goodsid %>&name=pay">购买</a>
       /<a href="servlet/BuyServlet?goodsid=<%=goodsid %>&name=add">添加到购物车</a></td>  
       </tr>
       <%
	}
	}
	//当用户没有登录时 
	else{
	%>
	<td><a href="Login.jsp?goodsid=<%=goodsid %>&name=pay">购买</a>
       /<a href="Login.jsp?goodsid=<%=goodsid %>&name=add>">添加到购物车</a></td>  
       <%
	}
}
       %>
    </table>
    <a href="ShopCart.jsp">查看购物车</a>
  </body>
</html>
