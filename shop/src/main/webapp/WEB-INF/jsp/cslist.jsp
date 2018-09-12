<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>商品列表</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

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
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<!-- 一级分类显示 -->
				<c:forEach items="${categorylist}" var="c">
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath}/product/findByCid?cid=${c.cid}&page=0">${c.cname}</a>
						</dt>
						<!-- 显示二级分类 -->
						<c:forEach var="cs" items="${c.categorySeconds}">
							<dd>
								<a href="${pageContext.request.contextPath}/product/findByCsid?csid=${cs.csid}&page=0">${cs.csname}</a>
							</dd>

						</c:forEach>
					</dl>

				</c:forEach>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<div id="result" class="result table clearfix">
						<ul>
							<c:forEach items="${pageBean.content}" var="p">
								<li>
									<a href="${pageContext.request.contextPath}/product/findByPid?pid=${p.pid}">
										<img src="${pageContext.request.contextPath}/${p.image}" width="170" height="170"  style="display: inline-block;">

										<span style='color:green'>
								       ${p.pname}
								</span>

										<span class="price">
									商城价： ￥${p.shop_price}
								</span>

									</a>
								</li>


							</c:forEach>

						</ul>
				</div>

				<div class="pagination">
					<c:if test="${pageBean.hasContent()==true}">
						第  ${pageBean.number+1}/${pageBean.getTotalPages()}页
					</c:if>
					<c:if test="${pageBean.hasContent()==false}">
						没有符合条件的数据
					</c:if>
					<c:if test="${pageBean.hasPrevious()==true}">
						<a href="${ pageContext.request.contextPath }/product/findByCsid?csid=${csid}&page=0" >首页</a>
						<a href="${ pageContext.request.contextPath }/product/findByCsid?csid=${csid}&page=${pageBean.number-1}" >上一页</a>
					</c:if>


					<c:if test="${pageBean.hasNext()==true}">
						<a href="${ pageContext.request.contextPath }/product/findByCsid?csid=${csid}&page=${pageBean.number+1}">下一页</a>
						<a href="${ pageContext.request.contextPath }/product/findByCsid?csid=${csid}&page=${pageBean.getTotalPages()-1}">尾页</a>
					</c:if>

				</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
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
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a >SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>