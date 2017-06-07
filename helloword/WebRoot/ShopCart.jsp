<%@page import="JavaBean.GoodsInfo"%>
<%@page import="JavaBean.GoodsItem"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Shoping Car page</title>
    
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
    <p>购物车界面</p>
    <%
    	String username=(String)session.getAttribute("username");
    	ArrayList<GoodsItem> shopcart=(ArrayList<GoodsItem>)session.getAttribute(username);
        float goodsTotlePrice=(float)0;
        if(shopcart==null){
        	out.print("您还没有选择商品");
       		RequestDispatcher rd=request.getRequestDispatcher("GoodsList.jsp");
        }
        else{
    %>
    <table Border=1>
    <tr>
    <td>商品ID</td><td>商品名称</td><td>数量</td><td>总价</td><td>操作</td>
    </tr>
    <%
    	GoodsInfo goods=null;
        GoodsItem goodsitems=null;
        
        for(int i=0;i<shopcart.size();i++){
        	goodsitems=shopcart.get(i);
        	goods=goodsitems.getGoods();
        	goodsTotlePrice=goodsTotlePrice+goods.getPrice()*goodsitems.getCount();
    %>
	<tr>
	<td><%= i+1%></td>
	<td><%=goods.getGoods_name() %></td>
	<td><%=goodsitems.getCount() %>    <a href="servlet/ChangeAmountServlet?goodsid=<%=goods.getGoods_ID() %>&style=add">增加</a>   <a href="servlet/ChangeAmountServlet?goodsid=<%=goods.getGoods_ID() %>&style=decrease">减少</a></td>
	<td><%=goodsitems.getTotalprice() %></td>
	<td><a href="servlet/DeleteGoodsServlet?goodsid=<%=goods.getGoods_ID() %>">删除</a></td>
    <% 
  
    	}
    }
     %>
     </table>

     <p>总价：<%=goodsTotlePrice %></p>
     <a href="GoodsList.jsp">继续选购商品</a>  <a href="servlet/ClearShoppingCartServlet">清空购物车</a>
  </body>
</html>
