<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
	<head>
		<base href="<%=basePath%>">
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />    
    	  	
        <link rel="stylesheet" type="text/css" href="css/user/top.css"/>
        <script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>                
        
        <link rel="stylesheet" type="text/css" href="js/user/artDialog-5.0.3/skins/default.css" />
        <script src="js/user/artDialog-5.0.3/artDialog.min.js"></script>
        
        <script src="js/user/nav.js"></script>
        <script type="text/javascript">  
		    function refresh() {  
		        //IE存在缓存,需要new Date()实现更换路径的作用  
		        document.getElementById("image").src="user/CreateCodeServlet?"+new Date();  
		    }  
		    
		    /* 注销 */
			function logout() {
				$.post("user/UserLogoutServlet", function() {
					location.reload();//刷新页面
				});//注销
			}
		</script>  
    </head>

    <body>
    	<div class="nav">
	    	<div class="ui_plugins">
	            <a class="logo" title="格瓦拉生活网" href="/">
	                <img width="102px" height="40px;" alt="格瓦拉生活网" src="images/other/blank.gif" />                
	            </a>
	            <div id="city_choose" class="ui_changeCity">
	                    <b class="ui_cityChoose" title="点击切换城市">重庆</b>
	            </div>
	            <div class="ui_nav_menu">
	                <ul class="ui_inline">
	                    <li class="isDisabled"><a href="user/IndexServlet">首页</a></li>
	                    <li><a href="user/HotMovieServlet">热映</a></li>
	                    <li><a href="user/FutureMovieServlet">即将上映</a></li>
	                    <li><a href="user/LoadCinemasServlet">电影院</a></li>
	                </ul>
	            </div>
	            <div class="ui_nav_right">
	                <div class="ui_t_search">
	                    <form id="gForm" action="" method="post">
	                        <input id="skey" class="search_text" autocomplete="off" placeholder="电影、演员、导演.." value="" name="skey" maxlength="40" size="40"/>                       
	                        <label class="search_button">
	                            <input class="search_button" type="submit" value=""/>                          
	                        </label>
	                    </form>
	                </div>
	                <c:choose>
	                	<c:when test="${username == null}">
		                	<div id="global_check_login" class="ui_login">
			                    <img class="ui_dHead" width="30px" height="30px" src="images/other/default_head.png"/>
			                    <a id="loginHref" href="javascript:void(0);">登录</a>&nbsp;<span>|</span>
			                    <a id="registerHref" href="user/register.html" target="_blank">注册</a>
			                </div>
		                </c:when>
		                <c:otherwise>
			                <div id="global_user_infobox" class="ui_login">
								<img width="30" height="30" src="images/other/blank.gif" style="background:url(images/head/h004.jpg) center center no-repeat;" alt="" class="ui_dHead">
								<a target="_blank" href="user/personIndex.jsp" title="${username}" class="ismessge"><b>${username}</b></a>
								<a href="javascript:logout();" title="注销" target="_top"><b>注销</b></a>
							</div>
						</c:otherwise>
	                </c:choose>                				
	            </div>
	         </div>    
	     </div>
	     
	     <!-- 登录悬浮框 -->
	     <div class='boxy' style="display:none;" id="boxy">
             <form id='jvForm' method='post' action='user/UserLoginServlet' target='loginframe' onsubmit='return checkInfo();'>
                <input id='username' name='username' type='text' style='color:#666666' placeholder="用户名" maxlength="20" />
                <div style="width:100%; height:10px;">&nbsp;</div>
                <p class="errorInfo">账号或密码错误!</p>    
                <input id='password' type='password' class='input1' name='password' maxlength="16" placeholder="登录密码"/>
                <p class="errorInfo">&nbsp;</p>
                <input id='validateCode' name='validateCode' type='text' maxlength="4" style='color:#666666' placeholder="输入验证码" />                 
                <img id="image" src="user/CreateCodeServlet" alt="验证码" onclick="refresh()" style="position:relative; top:-3px;" width="80px"  height="30px"/>
                <a href="javascript:refresh()" class="next" title="看不清楚，请点击更换一张图片">换一张</a>  
                <p class="errorInfo">验证码错误!</p>
                <div class='boxy-div2'>                     
                    <input type='submit' class='btn1' value='登&nbsp;&nbsp;录'/>
                    <a class="register" href="user/register.html">免费注册</a>
                </div>
             </form>
        </div>
    </body>
</html>
