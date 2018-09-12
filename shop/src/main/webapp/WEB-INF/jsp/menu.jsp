<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${ pageContext.request.contextPath }/index">首页</a>
						|
					</li>
					<c:forEach items="${categorylist}" var="one">

						<li>
							<a href="${ pageContext.request.contextPath }/product/findByCid?cid=${one.cid}&page=0">${one.cname}</a>
							|
						</li>
					</c:forEach>

		</ul>
	</div>