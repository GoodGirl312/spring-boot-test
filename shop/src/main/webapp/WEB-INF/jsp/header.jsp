<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>

				<c:choose>
					<c:when test='${sessionScope.get("exitUser")!=null}'>
						<li id="headerLogin" class="headerLogin" style="display: list-item;">

							${sessionScope.get("exitUser").name}|
						</li>
						<li id="headerLogin" class="headerLogin" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/order/orderList?uid=${sessionScope.get("exitUser").uid}">我的订单</a>|
						</li>
						<li id="headerRegister" class="headerRegister" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/user/quit">退出</a>|
						</li>
					</c:when>
					<c:otherwise>
						<li id="headerLogin" class="headerLogin" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/user/loginPage">登录</a>|
						</li>
						<li id="headerRegister" class="headerRegister" style="display: list-item;">
							<a href="${ pageContext.request.contextPath }/user/registPage">注册</a>|
						</li>
					</c:otherwise>
				</c:choose>

				
				
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${ pageContext.request.contextPath }/cart/showCart">购物车</a>
		</div>
		<div class="phone">
			客服热线:
			<strong>96008/53277764</strong>
		</div>
	</div>