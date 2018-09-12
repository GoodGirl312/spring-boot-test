<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>

	</HEAD>
	<body>
		<br/>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">

</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgcolor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="8%">
										用户名
									</td>
									<td align="center" width="8%">
										密码
									</td>
									<td align="center" width="8%">
										真实姓名
									</td>
									<td align="center" width="15%">
										email
									</td>
									<td align="center" width="15%">
										phone
									</td>
									<td align="center" width="15%">
										地址
									</td>
									<td align="center" width="5%">
										性别
									</td>
									<td align="center" width="8%">
										是否激活
									</td>

									<td  align="center"  width="7%">
										删除
									</td>

								</tr>
								<c:forEach items="${uList }" var="u" varStatus="status">
								  <tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.username }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.password }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.email }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.phone }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.addr }
											</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												${u.sex }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<c:if test="${u.state==1 }">
												   已激活
												</c:if>
												<c:if test="${u.state==0 }">
												   未激活
												</c:if>
												
											</td>

											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/user/delete?uid=${u.uid}" onclick="return window.confirm('确定删除')">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand"/>
												</a>
											</td>
										</tr>
								</c:forEach>
							</table>
							<div class="pagination">
								<c:if test="${pageBean.hasContent()==true}">
									第  ${pageBean.number+1}/${pageBean.getTotalPages()}页
								</c:if>
								<c:if test="${pageBean.hasContent()==false}">
									没有符合条件的数据
								</c:if>
								<c:if test="${pageBean.hasPrevious()==true}">
									<a href="${ pageContext.request.contextPath }/user/adminFindAll?page=0" class="firstPage">首页</a>
									<a href="${ pageContext.request.contextPath }/user/adminFindAll?page=${pageBean.number-1}" class="previousPage">上一页</a>
								</c:if>


								<c:if test="${pageBean.hasNext()==true}">
									<a class="nextPage" href="${ pageContext.request.contextPath }/user/adminFindAll?page=${pageBean.number+1}">下一页</a>
									<a class="lastPage" href="${ pageContext.request.contextPath }/user/adminFindAll?page=${pageBean.getTotalPages()-1}">尾页</a>
								</c:if>

							</div>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

