<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
pageContext.setAttribute("path", request.getContextPath());

%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="手机小说，网络小说，免费小说，都市小说，言情小说，玄幻小说，网游小说，军事小说，历史小说，穿越小说"
        />
        <meta name="description" content="看书网,专业原创小说网站,提供最新言情,都市校园,穿越,网游,玄幻,武侠,科幻,历史等小说免费阅读和小说下载,是最好看的小说阅读网。看小说,就上流溪阁书屋！"
        />
        <link rel="canonical" href="index" />
        <title>
            流溪阁书屋小说网站
        </title>
        <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1,user-scalable=no">
        <link href="img/logo_lxg.png" rel="apple-touch-icon-precomposed" />
        <link href="img/logo_lxg.png" rel="icon" type="image/x-icon" />
        <link href="img/logo_lxg.png" rel="shortcut icon" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/loadingBar.css">
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script> 
        <script type="text/javascript" src="script/easying.js"></script>
        <script src="script/jquery.beattext.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('p#roloadText').beatText({isAuth:true,beatHeight:"1em",isRotate:false,upTime:100,downTime:100});
            });
        </script>
<!-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">    -->
<!-- <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
      -->

        <link rel="stylesheet" href="css/style.css">
<!-- <script src="script/jquery.js"></script>   -->
<!-- <link rel="stylesheet" href="css/jquery.mobile-1.3.2.min.css" />  -->

<!-- <script src="script/jquery.mobile-1.3.2.min.js"></script> -->
<!--  <script  src="script/main.js?1234"></script> -->
    </head>
    <script type="text/javascript">
        $(function () {
            $("input").click(function() {
                // alert($(this).attr('value'));
                if ($(this).attr('value')=="请输入作者或小说名") {
                    $(this).attr('value', '');
                }
            
        });
    })
        
    </script>
    <body>
        <script src="script/require.js">
        </script>
        <div class="top">
            <h1 class="fl">
                <img src="img/logo_lxg.png" width="76" height="32" />
            </h1>
           <!--  <p class="fr">
               <a href="login.html" class="icon_user"
               title="个人中心">
                   个人中心
               </a>
           </p> -->
        </div>
        <div class="top_menu">
            <div class="topnav" >
                <a href="index.html" class="cur">首页 <b></b></a>
                <!-- <a href="nspd.html">男生<b></b></a> -->
                <!-- <a href="nvshengpd.html">女生<b></b></a> -->
                <!-- <a href="chubanshe.html">免费<b></b></a> -->
                <!-- <a href="login.html">书架<b></b></a> -->
            </div>

        </div>

       <!--  <div class="top_sign">
           <span class="usr_grade">
               登录查看我的等级
           </span>
           <span class="usr_sign">
               <a href="login.html">登录</a>
           </span>
       </div> -->
        <div class="search">
            <div class="searchbox mt10 clearfix">
                <form action="searchNovel.action" method="post" id="inputForm">
                    <input type="hidden" name="index" value="0" />
                    <input name="kw" type="text" class="t_i" placeholder="搜书搜作者" autocomplete="off"
                    value="请输入作者或小说名" />
                    <input type="hidden" name="search" value="aname" />
                    <div class="searchbtn">
                        <span class="t_b">
                        </span>
                        <span class="t_t">
                            搜索
                        </span>
                        <input class="clearfix" type="button" value="" />
                    </div>
                </form>
            </div>
        </div>
        <div class="section">
            <h3 class="title">
                <span class="b_l">
                </span>
            </h3>
            <div class="blist">
            <ul>
            <c:forEach var="encryptNovel" items="${encryptNovels}" varStatus="status">
            <li>
           <a href='/novel/novelinfo/bookInfo.html?key=${encryptNovel.novel.id}&encr=${encryptNovel.novel.chapterUrl}&keys={"chapterUrl1":${encryptNovel.keys.novelUrl1},"chapterUrl2":${encryptNovel.keys.chapterUrl2}}'>
    		<div class="bintro pl10"> ${encryptNovel.novel.name}
    		</h4><p>${encryptNovel.novel.type} . (${encryptNovel.novel.status}) . ${encryptNovel.novel.length}字
    			<br>${encryptNovel.novel.introduction}
    		</p>
    		</div>
    		</a>
            </li>
			</c:forEach>
			</ul>
            <ul id="indexRecommendNovels">
                <div class="loadingBar">
                    <div id="colorfulPulse">
                        <span class="item-1"></span>
                        <span class="item-2"></span>
                        <span class="item-3"></span>
                        <span class="item-4"></span>
                        <span class="item-5"></span>
                        <span class="item-6"></span>
                        <span class="item-7"></span>
                    </div>
                    <div class="container">
                        <p id="roloadText">正在加载中...</p>
                    </div>
                </div>

            </ul>
            </div>
        </div>
        <div class="block">

          <!--   <p class="item ">
          
              <a href="marketing/go-14.html@cnl=kanshu&sid=-jlfcT4pLcKDMWGdK6tas1" class="t_b btn_vip">
          
                  开通VIP，手机全站免费畅读！
          
              </a>
          
          </p> -->

        </div>
       
        <div class="search">

            <div class="searchbox mt10 clearfix">
                <form action="/3g/search?cnl=kanshu" method="get">
                    <input type="hidden" name="sid" value="-jlfcT4pLcKDMWGdK6tas1" />
                    <input name="kw" type="text" class="t_i" placeholder="搜书搜作者" autocomplete="off"
                    value="" />
                    <input type="hidden" name="search" value="aname" />
                    <div class="searchbtn">
                        <span class="t_b">
                        </span>
                        <span class="t_t">
                            搜索
                        </span>
                        <input type="submit" value="" />
                    </div>
                </form>
            </div>
        </div>
        <div class="footer home-bg">
            <div class="footer_nav clearfix">
                <a href="#">首页</a>
                <a href="#">书架</a>
                <a href="#">分类</a>
                <a href="#">留言</a>
                <a href="#">个人中心</a>
            </div>
            <p class="edition mt20">
                <span>
                    <a href="#">
                        普通版
                    </a>|
                    <a href="##" class="select"></a>
                </span>
            </p>
            <p class="copyright mt20">
                <b>流溪阁书屋</b>
            </p>
            <span id="gotop" class="icons icon-gotop">
            </span>
        </div>
        <script src="script/common.js"></script>
        <div class="pop_cover">
        </div>
        <div class="pop_tip">
            <div class="pop_inner">
                <div class="pop_cont">
                </div>
            </div>
        </div>
    </body>
</html>