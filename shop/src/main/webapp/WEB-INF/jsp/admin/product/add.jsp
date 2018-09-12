<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery.datepick.css" type="text/css"/>
	</HEAD>
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/product/save"
			  method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellspacing="1" cellpadding="5" width="100%" align="center" bgcolor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgcolor="#afd1f3" colspan="4"
						height="26">
						<strong><STRONG>添加商品</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgcolor="#f5fafe" class="ta_01">
						商品名称：
					</td>
					<td class="ta_01" bgcolor="#ffffff" colspan="3">
						<input type="text" name="pname" value="" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#f5fafe" class="ta_01">
						 商城价格：
					</td>
					<td class="ta_01" bgcolor="#ffffff">
						<input type="text" name="shop_price" value="" id="logonPwd"/>
					</td>
					<td align="center" bgcolor="#f5fafe" class="ta_01">
						市场价格：
					</td>
					<td class="ta_01" bgcolor="#ffffff">
						<input type="text" name="market_price" value="" id="userAction_save_do_userName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#f5fafe" class="ta_01">
						是否是热门商品：
					</td>
					<td class="ta_01" bgcolor="#ffffff">
						<input type="radio" name="is_hot" checked="checked" value="1"/>是
						<input type="radio" name="is_hot"  value="0"/>否
					</td>
					<td align="center" bgcolor="#f5fafe" class="ta_01">
						二级分类：
					</td>
					<td class="ta_01" bgcolor="#ffffff">
						
						<select name="categorySecond.csid" >
						  <c:forEach items="${csList }" var="cs">
						  	 <option value="${cs.csid }">${cs.csname }</option>
						  </c:forEach>

						</select>

					</td>
				</tr>
				
				
				<tr>
					<td align="center" bgcolor="#f5fafe" class="ta_01">
						商品图片：
					</td>
					<td class="ta_01" bgcolor="#ffffff" colspan="3">
						<input type="file" name="upload" size="30" value="" id="userAction_save_do_upload"/>
					</td>
				</tr>
				<TR>
					<TD class="ta_01" align="center" bgcolor="#f5fafe">
						商品描述：
					</TD>
					<TD class="ta_01" bgcolor="#ffffff" colspan="3">
						<textarea name="pdesc" cols="30" rows="3" id="userAction_save_do_remark" style="WIDTH: 96%"></textarea>
					</TD>
				</TR>
				<TR>
					<td align="center" colspan="4" class="sep1">
						<img src="${pageContext.request.contextPath}/images/shim.gif"/>
					</td>
				</TR>


				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgcolor="#f5fafe" colspan="4">
						<button type="submit" id="userAction_save_do_submit" name="submit" value="&#30830;&#23450;" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="&#37325;&#32622;" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>