 var ks_host = "3g";

var ks_uid = 0;
$(function(){
    
   function showImgNovel(img){
    alert(img);
   }
   function getNovels(data){
    var novels=[];
    for (var i = data.length - 1; i >= 0; i--) {
      novels[i]=data[i]["novel"];
    }
    return novels;
   }
   function getKeys(data){
    var keys={};
    for (var i = data.length - 1; i >= 0; i--) {
      keys[i]=data[i]["keys"];
    }
    return keys;
   }

   function showImgNovel2html(data,keys){
    // console.log(keys);
    // alert(keys);
    if(data["status"]==1)status="连载中";else status="完结";
    if(data["length"]>=10000)length=parseInt(parseInt(data["length"])/1000)/10.0+"万";else length=data["length"];
    var hDiv="<a href='"+"/novel/novelinfo/bookInfo.html?key="+data["id"]+"&encr="+data["chapterUrl"]+"&keys="+keys+"'>"
    +"<div class=\"bcover fl\"> "
    +"<img src=\""+data["img"]+"\" onerror=\"this.src='http://img.kanshu.com/articleInfo/images/nopic.jpg';this.onerror='';\""
    +"alt=\"\" height=\"130\" width=\"85\" /> "
    +"</div>"
    +"<div class=\"bintro pl10\"> "
    +data["name"]
    +"</h4> "
    +"<p> "
    +data["type"]+". ("+status+") . "+length+"字 "
    +"<br>"
    +data["introduction"].trim()
    +"</p>"
    +"</div>"
    +"</a>";
    return hDiv;
   }

   function showNovel2html(data,keys){
    if(data["status"]==1)status="连载中";else status="完结";
    if(data["length"]>=10000)length=parseInt(parseInt(data["length"])/1000)/10.0+"万";else length=data["length"];
     var hDiv= "<a href='"+"/novel/novelinfo/bookInfo.html?key="+data["id"]+"&encr="+data["chapterUrl"]+"&keys="+keys+"'>"
      +"<div class=\"bintro\"> "
      +"<h4> "
      +data["name"]
      +"</h4>"
      +"<p>"
      +data["type"]+" . ("+status+") . "+length+"字 "
      +"<br> "
      +"</p> "
      +"</div> "
      +"</a> ";
      return hDiv;
    }

    //封装ajax代码
    function ajax_datatypeByJson(type, url, json_data, succ_func,err_func) {//data数据可以为空
      $.ajax({
          type: type,
          url: url,
          dataType: "json",
          data: json_data,
          error: function (data) {
              //请求失败时被调用的函数 
              err_func(data);
          },
          success: function (data) {
              succ_func(data);
          }
      });
    }
  // 首页推荐小说
  ajax_datatypeByJson('GET','/novel/getIndexRecommendNovels.action',{'index':0},getIndexRecommendNovels,function(){});

  function getIndexRecommendNovels(data) {
    console.log(data);
    hRecommend="";
    keys= getKeys(data);
    data= getNovels(data);
    console.log("keys:"+JSON.stringify(keys[1])+"");

    for (var i =0;i< 3 ; i++) {
      hRecommend+="<li>"+showImgNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
    }
    for (var i =3;i< data.length ; i++) {
      hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
    }
    $("#indexRecommendNovels").html(hRecommend);
  }

  
    function showHotNovel2html(data,keys){
    if(data["status"]==1)status="连载中";else status="完结";
    if(data["length"]>=10000)length=parseInt(parseInt(data["length"])/1000)/10.0+"万";else length=data["length"];

    var hDiv= "<a href='"+"/novel/novelinfo/bookInfo.html?key="+data["id"]+"&encr="+data["chapterUrl"]+"&keys="+keys+"'>"
      +"<div class=\"bintro\"> "
      +"<h4> "
      +data["name"]
      +"</h4>"
      +"<p>"
      +data["type"]+" . ("+status+") . "+length+"字 "
      +"<br> "
      +data["introduction"].trim()
      +"</p> "
      +"</div> "
      +"</a> ";
      return hDiv;
  }

   // 热门小说
    var hotMoreIndex=0;
    function getHotNovels(data) {
      keys= getKeys(data);
      data= getNovels(data);
      hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
      for (var i =1;i< data.length ; i++) {
        hRecommend+="<li>"+showHotNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
      }
      $("#hotNovels").html(hRecommend);
    }
    ajax_datatypeByJson('GET','/novel/getHotNovels.action',{'index':hotMoreIndex},getHotNovels,function(){});

    // 热门更多，绑定点击事件
    getHotNovelsClick0()
    function getHotNovelsClick0() {
      $("#hotMore").off("click").click(function() {
        hotMoreIndex++;
        function getHotNovelsClick(data){
          keys= getKeys(data);
          data= getNovels(data);
          hRecommend="";
          for (var i =1;i< data.length ; i++) {
            hRecommend+="<li>"+showHotNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
          }
          var pre=$("#hotMore").closest('ul').prev("ul").html();
          $("#hotMore").closest('ul').prev("ul").html(pre+hRecommend);
        }
        ajax_datatypeByJson('GET','/novel/getHotNovels.action',{'index':hotMoreIndex},getHotNovelsClick,function(){});
      });
    }
    
    // 粉丝推荐
    var fanMoreIndex=0;
    function getFanNovels(data) {
      keys= getKeys(data);
      data= getNovels(data);
      hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
      for (var i =1;i< data.length ; i++) {
        hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
      }
      $("#fanNovels").html(hRecommend);
    }
    ajax_datatypeByJson('GET','/novel/getFanNovels.action',{'index':fanMoreIndex},getFanNovels,function(){});

      // 粉丝推荐更多，绑定点击事件
    getFanNovelsClick0()
    function getFanNovelsClick0() {
      $("#fanMore").off("click").click(function() {
        fanMoreIndex++;
        function getFanNovelsClick(data){
          keys= getKeys(data);
          data= getNovels(data);
          hRecommend="";
          for (var i =1;i< data.length ; i++) {
            hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
          }
          var pre=$("#fanMore").closest('ul').prev("ul").html();
          $("#fanMore").closest('ul').prev("ul").html(pre+hRecommend);
        }
        ajax_datatypeByJson('GET','/novel/getFanNovels.action',{'index':fanMoreIndex},getFanNovelsClick,function(){});
      });
    }
    
    // 男生推荐
    var boyMoreIndex=0;
    function getBoyNovels(data) {
      keys= getKeys(data);
      data= getNovels(data);
      hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
      for (var i =1;i< data.length ; i++) {
        hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
      }
      $("#boyNovels").html(hRecommend);
    }
    ajax_datatypeByJson('GET','/novel/getBoyNovels.action',{'index':boyMoreIndex},getBoyNovels,function(){});

      // 男生推荐更多，绑定点击事件
    getBoyNovelsClick0()
    function getBoyNovelsClick0() {
      $("#boyMore").off("click").click(function() {
        boyMoreIndex++;
        function getBoyNovelsClick(data){
          keys= getKeys(data);
          data= getNovels(data);
          hRecommend="";
          for (var i =1;i< data.length ; i++) {
            hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
          }
          var pre=$("#boyMore").closest('ul').prev("ul").html();
          $("#boyMore").closest('ul').prev("ul").html(pre+hRecommend);
          // alert(pre);
        }
         ajax_datatypeByJson('GET','/novel/getBoyNovels.action',{'index':boyMoreIndex},getBoyNovelsClick,function(){});

      });
    }
    
     
     // 女生推荐
     var girlMoreIndex=0;
    function getGirlNovels(data) {
      keys= getKeys(data);
      data= getNovels(data);
      hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
      for (var i =1;i< data.length ; i++) {
        hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
      }
      $("#girlNovels").html(hRecommend);
    }
    ajax_datatypeByJson('GET','/novel/getGirlNovels.action',{'index':girlMoreIndex},getGirlNovels,function(){});
    // 女生推荐更多，绑定点击事件
    getGirlNovelsClick0()
    function getGirlNovelsClick0() {
      $("#girlMore").off("click").click(function() {
        girlMoreIndex++;
        function getGirlNovelsClick(data){
          keys= getKeys(data);
          data= getNovels(data);
          hRecommend="";
          for (var i =1;i< data.length ; i++) {
            hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
          }
          var pre=$("#girlMore").closest('ul').prev("ul").html();
          $("#girlMore").closest('ul').prev("ul").html(pre+hRecommend);
        }
         ajax_datatypeByJson('GET','/novel/getGirlNovels.action',{'index':girlMoreIndex},getGirlNovelsClick,function(){});
      });
    }
    
     







     // 免费推荐
     var otherMoreIndex=0;
    function getOtherNovels(data) {
      keys= getKeys(data);
      data= getNovels(data);
      hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
      for (var i =1;i< data.length ; i++) {
        hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
      }
      $("#otherNovels").html(hRecommend);
    }
    ajax_datatypeByJson('GET','/novel/getOtherNovels.action',{'index':otherMoreIndex},getOtherNovels,function(){});
    // 免费推荐更多，绑定点击事件
    getOtherNovelsClick0()
    function getOtherNovelsClick0() {
      $("#otherMore").off("click").click(function() {
        otherMoreIndex++;
        function getOtherNovelsClick(data){
          keys= getKeys(data);
          data= getNovels(data);
          hRecommend="";
          for (var i =1;i< data.length ; i++) {
            hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
          }
          var pre=$("#otherMore").closest('ul').prev("ul").html();
          $("#otherMore").closest('ul').prev("ul").html(pre+hRecommend);
        }
         ajax_datatypeByJson('GET','/novel/getOtherNovels.action',{'index':otherMoreIndex},getOtherNovelsClick,function(){});
      });
    }
    
     // 免费推荐
     // $.ajax({
     //      url: '/novel/getOtherNovels.action',
     //      type: 'GET',
     //      dataType: 'json',
     //      data:{'index':0},
     //      success:function(data){
     //        keys= getKeys(data);
     //        data= getNovels(data);

     //        hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
     //        for (var i =1;i< data.length ; i++) {
     //          hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
     //        }
     //        // console.log(showImgNovel2html($(data)[0]));
     //        $("#otherNovels").html(hRecommend);
            
     //        },
     //       error:function(){
     //           // alert('服务器超时，请重试！');
     //       }
     //    });

})