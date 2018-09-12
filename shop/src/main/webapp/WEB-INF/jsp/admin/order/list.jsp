<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<c:forEach items="${pageBean.content}" var="order">
									<tr
											style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

										<td align="left" width="10%" colspan="5">
											订单号:${order.oid} &nbsp;&nbsp;&nbsp;&nbsp;
											订单金额:${order.total} &nbsp;&nbsp;&nbsp;&nbsp;
											订单状态:
											<c:choose>
												<c:when test="${order.state==1}">
													未付款
												</c:when>
												<c:when test="${order.state==2}">
													<a href="${ pageContext.request.contextPath }/order/adminUpdateState?oid=${order.oid}"><font color="red">发货</font></a>

												</c:when>
												<c:when test="${order.state==3}">
													已经发货
												</c:when>
												<c:otherwise>
													订单完成
												</c:otherwise>

											</c:choose>

										</td>
									</tr>
									<tr onmouseover="this.style.backgroundColor = 'white'"
										onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="10%">
											序号
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">
											图片
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">
											名称
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="8%">
											数量
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="23%">
											小计
										</td>
									</tr>
									<c:forEach var="orderItem" items="${order.orderItems}" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${status.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<img width="45" height="50" src="${pageContext.request.contextPath }/${orderItem.product.image}"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${orderItem.product.pname}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="8%">
												${orderItem.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="23%">
												${orderItem.subtotal}
											</td>


										</tr>
									</c:forEach>

								</c:forEach>

									<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%" colspan="8">

										<div class="pagination">
											<c:if test="${pageBean.hasContent()==true}">
												第  ${pageBean.number+1}/${pageBean.getTotalPages()}页
											</c:if>
											<c:if test="${pageBean.hasContent()==false}">
												没有符合条件的数据
											</c:if>
											<c:if test="${pageBean.hasPrevious()==true}">
												<a href="${ pageContext.request.contextPath }/order/adminFindAll?page=0" class="firstPage">首页</a>
												<a href="${ pageContext.request.contextPath }/order/adminFindAll?page=${pageBean.number-1}" class="previousPage">上一页</a>
											</c:if>


											<c:if test="${pageBean.hasNext()==true}">
												<a class="nextPage" href="${ pageContext.request.contextPath }/order/adminFindAll?page=${pageBean.number+1}">下一页</a>
												<a class="lastPage" href="${ pageContext.request.contextPath }/order/adminFindAll?page=${pageBean.getTotalPages()-1}">尾页</a>
											</c:if>

										</div>
									</td>
									
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

