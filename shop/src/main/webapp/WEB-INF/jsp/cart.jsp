<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>购物车</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/number.js"></script>
	<style type="text/css">
		td{
			text-align: center;
		}

	</style>
</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png" />
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>
</div>	
<div class="container cart">
		<div class="span24">
			<c:choose>
				<c:when test="${sessionScope.get('cart')==null || sessionScope.get('cart').cartItems.size()==0}">
					<div class="step step1">
						您还没有购物!请先去购物!
					</div>

			    </c:when>
				<c:otherwise>
					<table>
						<tbody>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
						<c:if test="${sessionScope.get('cart').getCartItems()!=null}">

						<c:forEach  items="${sessionScope.get('cart').getCartItems()}" var="m">
							<tr>
								<td width="60">
									<img src="${pageContext.request.contextPath}/${m.product.image}"/>
								</td>
								<td>
									<a target="_blank">${m.product.pname}</a>
								</td>
								<td>
									￥${m.product.shop_price}
								</td>
								<td class="quantity" width="0">
									<input  onclick="num('+')" value="+"/>
										<input type="text" id="count" value="${m.count}"/>
									<input  onclick="num('-')" value="-"/>
								</td>
								<td width="140">
									<span class="subtotal">￥${m.subtotal}</span>
								</td>
								<td>
									<a href="${ pageContext.request.contextPath }/cart/removeFromCart?pid=${m.product.pid}" class="delete">删除</a>
								</td>
							</tr>
						</c:forEach>
						</c:if>

						</tbody>
					</table>
					<dl id="giftItems" class="hidden" style="display: none;">
					</dl>
					<div class="total">
						<em id="promotion"></em>
						<em>
							登录后确认是否享有优惠
						</em>
						赠送积分: <em id="effectivePoint">${sessionScope.get("cart").total}</em>
						商品金额: <strong id="effectivePrice">￥${sessionScope.get("cart").total}元</strong>
					</div>
					<div class="bottom">
						<a href="${ pageContext.request.contextPath }/cart/clearCart" id="clear" class="clear">清空购物车</a>
						<a href="${ pageContext.request.contextPath }/order/saveOrder" id="submit" class="submit">提交订单</a>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/>
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
				<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>