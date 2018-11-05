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

    // 首页推荐小说
    $.ajax({
          url: '/novel/getIndexRecommendNovels.action',
          type: 'GET',
          dataType: 'json',
          data:{'index':0},
          success:function(data){
            console.log(data);

            hRecommend="";
            keys= getKeys(data);
            data= getNovels(data);
            // keys= getKeys(data);
            // console.log($(keys));
            console.log("keys:"+JSON.stringify(keys[1])+"");

            for (var i =0;i< 3 ; i++) {
              hRecommend+="<li>"+showImgNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            for (var i =3;i< data.length ; i++) {
              hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            // console.log(showImgNovel2html($(data)[0]));
            $("#indexRecommendNovels").html(hRecommend);
            
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

    function showHotNovel2html(data){
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
var hotMoreIndex=0;
  // 热门小说
    $.ajax({
          url: '/novel/getHotNovels.action',
          type: 'GET',
          dataType: 'json',
          data:{'index':0},
          success:function(data){
            keys= getKeys(data);
            data= getNovels(data);
            // alert(JSON.stringify(keys[i]));

            hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
            for (var i =1;i< data.length ; i++) {
              hRecommend+="<li>"+showHotNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            // console.log(showImgNovel2html($(data)[0]));
            $("#hotNovels").html(hRecommend);
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

// 热门更多，绑定点击事件

        $("#hotMore").click(function() {
          var moreUL="";

          hotMoreIndex++;
          alert($(this).closest('ul').html());
        });

    // 粉丝推荐
     $.ajax({
          url: '/novel/getFanNovels.action',
          type: 'GET',
          dataType: 'json',
          data:{'index':0},
          success:function(data){
            keys= getKeys(data);
            data= getNovels(data);

            hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
            for (var i =1;i< data.length ; i++) {
              hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            // console.log(showImgNovel2html($(data)[0]));
            $("#fanNovels").html(hRecommend);
            
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

     // 男生推荐
     $.ajax({
          url: '/novel/getBoyNovels.action',
          type: 'GET',
          dataType: 'json',
          data:{'index':0},
          success:function(data){
            keys= getKeys(data);
            data= getNovels(data);

            hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
            for (var i =1;i< data.length ; i++) {
              hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            // console.log(showImgNovel2html($(data)[0]));
            $("#boyNovels").html(hRecommend);
            
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

     // 女生推荐
     $.ajax({
          url: '/novel/getGirlNovels.action',
          type: 'GET',
          dataType: 'json',
          data:{'index':0},
          success:function(data){
            keys= getKeys(data);
            data= getNovels(data);

            hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
            for (var i =1;i< data.length ; i++) {
              hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            // console.log(showImgNovel2html($(data)[0]));
            $("#girlNovels").html(hRecommend);
            
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

     
     // 免费推荐
     $.ajax({
          url: '/novel/getOtherNovels.action',
          type: 'GET',
          dataType: 'json',
          data:{'index':0},
          success:function(data){
            keys= getKeys(data);
            data= getNovels(data);

            hRecommend="<li>"+showImgNovel2html($(data)[0],JSON.stringify(keys[0]))+"</li>";
            for (var i =1;i< data.length ; i++) {
              hRecommend+="<li>"+showNovel2html($(data)[i],JSON.stringify(keys[i]))+"</li>";
            }
            // console.log(showImgNovel2html($(data)[0]));
            $("#otherNovels").html(hRecommend);
            
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

})