<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
     var list=new Array();
    function clickZan(id){
        //谁都可以点赞。点击后，隐藏值设置1
            if ($.inArray(id, list)== -1) {
                //可以点赞
                $("#" + id + "zan").hide().next($("a")).show()
                list.push(id)
                //后台将赞的个数增加
                $.ajax({
                    type: "POST",
                    data: {
                        "id":id
                    },
                    dataType: "json",
                    url: "${pageContext.request.contextPath}/toClick",
                    success:function () {

                    }

                });
            }
    }

    function check(id){
        if(${sessionScope.exitUser==null}){
            alert("请先去登录!");
        }else{

            var content=$("#content_"+id).val()
            var parentID=$("#parentID").val()
            var pid=$("#pid").val()
            var uid=$("#uid").val()

            $.ajax({
                type: "POST",
                data: {
                    "parentID":parentID,
                    "product.pid":pid,
                    "content":content,
                     "user.uid": uid
                },
                url: "${pageContext.request.contextPath}/addComment",

                success: function (result) {
                    if (result.status == 200) {
                        alert(result.message);
                    }
                    if (result.status != 200) {
                        alert(result.message);
                    }
                }

            });

            var selector = 'div_'+id
            $("#"+selector).hide();

            var n= $("#replyNum"+id).text();
            n++;
            $("#replyNum"+id).text(n);
            alert("评价成功！")
           }
    }

    function reply(id){
        var selector = 'div_'+id
        if( $("#"+selector).is(":visible")){
            $("#"+selector).hide();

        }else {
            //清空内容
            $("#content_"+id).val("");
            $("#" + selector).show();
            var arr = document.getElementsByClassName("rep");
            for (var i = 0; i < arr.length; i++) {
               var b=arr[i].getAttribute("id");

                if (b!= selector) {

                    $("#"+b).hide()
                }
            }

        }
    }

</script>

  <c:forEach items="${product.comments}" var="c">
    <c:if test="${c.parentID==0}">

     <div>
         <div style="width:400px;height:69px; padding-left:10px; padding-top:5px;border-radius:49px 0px 49px 0px ;">
             <div style="width: 74px; height: 74px; float:left; border-radius: 50%; border: 3px solid #eee; overflow: hidden;">
                 <img src="//tva3.sinaimg.cn/crop.0.0.180.180.50/45221c62jw1e8qgp5bmzyj2050050aa8.jpg" width="80" height="80" />
             </div>
             <span style="color: #FF9523;font-size: 15px"> ${c.user.username}</span>
             <div style="align:center;font-size: 12px;position: relative;padding-top: 20px"> &nbsp;&nbsp;${c.content}</div>
           </div>


         <div style="margin-left: 100px;font-size: 12px">${c.commentDate}
             <div style="float:right">
                 <span><img src="${pageContext.request.contextPath}/image/hand.png" onclick="clickZan(${c.id})">
                     <a id="${c.id}zan" href="javascript:void(0);" onclick="clickZan(${c.id})" >赞 ${c.goodNum}</a>
                     <a style="display: none;color: red;" href="javascript:void(0);" >已赞 ${c.goodNum+1}</a>
                     |</span>
                 <span onclick="reply(${c.id})"><a href="javascript:void(0);" >回复(<span id="replyNum${c.id}">${c.reply}</span>)</a></span>
             </div>
         </div>
         <hr/>
         <div style="display: none" id="div_${c.id}" class="rep">

             <form action="" method="post">
                 <input id="parentID" type="hidden" name="parentID" value="${c.id}">
                 <input id="pid" type="hidden" name="product.pid" value="${c.product.pid}">
                 <textarea id="content_${c.id}" name ="content"  placeholder="回复${c.user.username}"  style="height: 40px;width: 99%; "></textarea>
                     <div style="text-align: right">
                         <c:choose>
                             <c:when test="${sessionScope.exitUser==null}">
                             <a  href="${pageContext.request.contextPath}/user/loginPage" style="color: black;">登录</a >
                             &nbsp;&nbsp;|
                             <a style="color: black;" href="${pageContext.request.contextPath}/user/registPage">注册</a>
                             </c:when>
                             <c:otherwise>
                                 <input id="uid" type="hidden" name="user.uid" value="${sessionScope.exitUser.uid}">
                             </c:otherwise>
                         </c:choose>
                         <input type="button" onclick="check(${c.id})" value="发布"/>
                     </div>
             </form>
         </div>

     </div>
    </c:if>
  </c:forEach>


