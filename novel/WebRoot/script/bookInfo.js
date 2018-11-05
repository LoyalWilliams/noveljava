
$(function(){
	function getKeys(data){
    var keys={};
    for (var i = data.length - 1; i >= 0; i--) {
      keys[i]=data[i]["keys"];
    }
    return keys;
   }
   function getChapters(data){
    var chapters=[];
    for (var i = data.length - 1; i >= 0; i--) {
      chapters[i]=data[i]["chapter"];
    }
    return chapters;
   }
	function parseUrl(searchURL){
		searchURL = decodeURIComponent(searchURL.substring(1, searchURL.length));
		// console.log(searchURL);
    	var splits = searchURL.split("&");
    	kvs={};
    	for (var i = splits.length - 1; i >= 0; i--) {
    		kv=splits[i].split("=");
    		kvs[kv[0]]=kv[1];
    	}
    	return kvs;
	}
	function getNovels(data){
    var novels=[];
    for (var i = data.length - 1; i >= 0; i--) {
      novels[i]=data[i]["novel"]
    }
    return novels;
   }
	function parseDate(date) {
		date = new Date(date);
		var month=date.getMonth()<10?"0"+date.getMonth():date.getMonth();
		var day=date.getDate()<10?"0"+date.getDate():date.getDate();
		var hours=date.getHours()<10?"0"+date.getHours():date.getHours();
		var minutes=date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes();
		var se=date.getSeconds()<10?"0"+date.getSeconds():date.getSeconds();
		return date.getFullYear()+"-"+month+"-"
			+day+" "+hours+":"+minutes;
	}
	var searchURL = window.location.search;
	var paras=parseUrl(searchURL);
	var key=paras["key"];
	// alert(paras["keys"]);
	console.log("keys:"+paras["keys"]);

	function getNovelStatus(data){
		if(data["status"]==1)status="连载中";else status="完结";
		return status;
	}
	function getNovelLength(data){
		if(data["length"]>=10000)length=parseInt(parseInt(data["length"])/1000)/10.0+"万";else length=data["length"];
		return length;
	}

	function showRightDiv(data){
		return "<p>"
          +"分类:"
          +"<b class=\"tname\">"
          +"<a href=\"\" class=\"ye\"> "
          +data["type"]
          +"</a> "
          +"</b> "
         +"<br/>"
         +"作者: "+data["author"]
         +" <br/>"
         +getNovelLength(data)+"字 共300章 "
         +"<br/>"
         +"收藏: "+data["collection"]+" &nbsp;&nbsp; 点击: "+data["totalClick"]
         +" <br/>"
         +"红花: "+data["weekRecommend"]+" &nbsp;&nbsp; 打赏: "+data["weekClick"]
         +" <br/>"
         +"推荐: "+data["totalRecommend"]
         +"</p> ";
	}

	function showUpdate(data) {
         return "<span class=\"bname\"> "
         +"<a href=\"\">"
         +"最新章节： "
         +"<b class=\"ye\"> "
         +data["lastUpdateChapter"].trim()
         +"</b> "
         +"</a> "
         +"</span>"
         +"更新时间： "
         +"<b class=\"gy\"> "
         +"("+parseDate(data["lastUpdateTime"])+"更新) "
         +"</b> ";
	}

	function showIntro(data) {
         return "<p> "
         +"<b>"
         +"简介： "
         +"</b> "
         +data["introduction"].trim()
         +"</p> ";
	}

    $.ajax({
          url: '/novel/binfo.action',
          type: 'GET',
          dataType: 'json',
          data:{'key':key},
          success:function(data){
          	console.log(data);

          	data= data["novel"];
          	console.log(data);
          	// $("#novelName").html();
          	// $("#novelStatus").html();
          	// data=data["novel"];
          	// alert(data);
          	var rightDiv=showRightDiv(data);
          	$("#picRight").html(rightDiv);
          	var updateDiv=showUpdate(data);
          	$("#update").html(updateDiv)
          	var introDiv=showIntro(data);
          	$("#intro").html(introDiv)

          	// alert(rightDiv);
          	$("#nameAndStatus").html(data["name"]+"<span >("+getNovelStatus(data)+")</span >")
          	// $("#novelLength").html(getNovelLength(data)+"字 共300章");
          	// $("#novelAuthor").html("作者: "+data["author"]);
          	// $("#novelType").html(data["type"]);
          	$("#novelPic").attr('src', data["img"]);
          	// $("#novelIntroduction").html(data["introduction"].trim());
          	// $("#lastUpdateChapter").html("最新章节："+data["lastUpdateChapter"].trim());
          	// // alert(data["lastUpdateChapter"].trim());
          	// $("#lastUpdateTime").html("("+data["lastUpdateTime"]+" 更新)");
          	
          	// console.log($("#readNow").attr('href'));

            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });

    // alert(paras["u"]);
// console.log({'encr':paras["encr"],keys:$.parseJSON(paras["keys"])});
// console.log("novelUrl1:"+$.parseJSON(paras["keys"]));
// // keys:{"novelUrl2":[51],"novelUrl1":[],"chapterUrl2":[55],"lastUpdateChapterUrl2":[55],"lastUpdateChapterUrl1":[],"chapterUrl1":[]}
// $k=$.parseJSON(paras["keys"])["novelUrl1"]
// console.log("novelUrl1isEmpty:" + isNaN($k)+ ($k=="")+"{}"+{})
// novelUrl1
// chapterUrl2
// EncrAndKeys    JSON.stringify(data[i])

    $.ajax({
          url: '/novel/chapterIndexList.action',
          type: 'post',
          dataType: 'json',
          data:{'encr':paras["encr"],keys:paras["keys"],offset:0,length:24},
          success:function(data){
          	// data= getNovels(data);
          	console.log(data);
          	var keys=getKeys(data);
          	data=getChapters(data);
          	var chapterLi="";
            $("#readNow").attr('href', "chapterContent.html?encr="+data[0].url+"&keys="+JSON.stringify(keys[0]));
          	for (var i = 0; i < data.length; i++) {
          		chapterLi+="<li><div class=\"msg\"><a href='chapterContent.html?encr="+data[i].url+"&keys="+JSON.stringify(keys[i])+"'>"+data[i].title+"</a></div></li>"          		
          		console.log("title:"+data[i].title+"url:"+data[i].url);
          	}
          	console.log(chapterLi);
          	$("#chapters").html(chapterLi);
          	
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });
// alert(0);
// <ul class="clist tab-content active" id="chapters">
                        // <li>
                            // <div class="msg">111111111111</div>
                        // </li>
   	 $.ajax({
          url: '/novel/chapterList.action',
          type: 'post',
          dataType: 'json',
          data:{'encr':paras["encr"],keys:paras["keys"]},
          success:function(data){
          	// data= getNovels(data);
          	// for (var i = data.length - 1; i >= 0; i--) {
          		// console.log("title:"+data[i].title+"url:"+data[i].url);
          	// }
          	// console.log("chapterList:"+data);
            console.log("yes");
          	console.log(data);
          	
            },
           error:function(){
               // alert('服务器超时，请重试！');
           }
        });





})