<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/product/addPage";
			}
		</script>
	</HEAD>
	<body>
		<br/>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
添加
</button>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="17%">
										商品图片
									</td>
									<td align="center" width="17%">
										商品名称
									</td>
									<td align="center" width="8%">
										商城价格
									</td>
									<td align="center" width="23%">
										市场价格
									</td>
									<td width="11%" align="center">
										是否热门
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								    <c:forEach items="${pageBean.content }" var="p" varStatus="status">
								      <tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												${status.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<img width="45" height="50" src="${pageContext.request.contextPath }/${p.image }"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${p.pname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="8%">
												${p.shop_price }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="23%">
												${p.market_price}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<c:choose>
													<c:when test="${p.isHot == 1 }">
														是
													</c:when>

													<c:otherwise>
														否
													</c:otherwise>
												</c:choose>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/product/edit?pid=${p.pid}">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand"/>
												</a>
											</td>
											
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/product/delete?pid=${p.pid}" onclick="return window.confirm('确定删除？')">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand"/>
												</a>
											</td>
										</tr>
								    </c:forEach>
									
									<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">


								</tr>
							</table>
							<div class="pagination">
								<c:if test="${pageBean.hasContent()==true}">
									第  ${pageBean.number+1}/${pageBean.getTotalPages()}页
								</c:if>
								<c:if test="${pageBean.hasContent()==false}">
									没有符合条件的数据
								</c:if>
								<c:if test="${pageBean.hasPrevious()==true}">
									<a href="${ pageContext.request.contextPath }/product/adminFindAll?page=0" class="firstPage">首页</a>
									<a href="${ pageContext.request.contextPath }/product/adminFindAll?page=${pageBean.number-1}" class="previousPage">上一页</a>
								</c:if>


								<c:if test="${pageBean.hasNext()==true}">
									<a class="nextPage" href="${ pageContext.request.contextPath }/product/adminFindAll?page=${pageBean.number+1}">下一页</a>
									<a class="lastPage" href="${ pageContext.request.contextPath }/product/adminFindAll?page=${pageBean.getTotalPages()-1}">尾页</a>
								</c:if>

							</div>

						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

