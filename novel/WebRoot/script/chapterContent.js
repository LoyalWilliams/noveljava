
$(function(){
	function getKeys(data){
    var keys={};
    for (var i = data.length - 1; i >= 0; i--) {
      keys[i]=data[i]["keys"];
    }
    return keys;
   }

	function parseUrl(searchURL){
		searchURL = decodeURIComponent(searchURL.substring(1, searchURL.length));
    	var splits = searchURL.split("&");
    	kvs={};
    	for (var i = splits.length - 1; i >= 0; i--) {
    		kv=splits[i].split("=");
    		kvs[kv[0]]=kv[1];
    	}
    	return kvs;
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
	console.log("keys:"+paras["keys"]);
  console.log("encr:"+paras["encr"])
  function getChapterKeys(keys,str) {
    chapterKey=[];
    for (var i = 0;i < keys[str].length ;  i++) {
      chapterKey[i]=keys[str][i];
    }
    return chapterKey;
  }
	 $.ajax({
      url: '/novel/chapterContent.action?',
      type: 'get',
      dataType: 'json',
      data:{'encr':paras["encr"],keys:paras["keys"]},
      success:function(data){
        console.log(data.keys);
      console.log("prev1:"+getChapterKeys(data.keys,"prev1"));
      var prevKeys={chapterUrl1:getChapterKeys(data.keys,"prev1"),
                chapterUrl2:getChapterKeys(data.keys,"prev2")};
      var nextKeys={chapterUrl1:getChapterKeys(data.keys,"next1"),
                chapterUrl2:getChapterKeys(data.keys,"next2")};
      console.log("keys:"+JSON.stringify(data.keys));
      console.log(JSON.stringify(prevKeys));
      // console.log("keys:"+JSON.stringify(data.chapterDetail));
      // alert(1);
      $("#content").html(data.chapterDetail.content);
      $("#title").html(data.chapterDetail.title);
      $(document).attr({title:data.chapterDetail.title});
      $("#nav_left").html("<a href='chapterContent.html?encr="+data.chapterDetail.prev+"&keys="+JSON.stringify(prevKeys)+"'>上一章</a>");
      $("#nav_right").html("<a href='chapterContent.html?encr="+data.chapterDetail.next+"&keys="+JSON.stringify(nextKeys)+"'>下一章</a>");
      // <a href='chapterContent.html?encr="+data[i].url+"&keys="+JSON.stringify(keys[i])+"'>"
      },
     error:function(){
      // $("#content").fadeOut('slow');
        $("#content").html('<div style="height: 100px;text-align: center;font-size: 16px;">服务器超时，请重试！</div>').show('slow');
        // $(this).fadeIn('slow', function() {
        //   $(this).show('slow');
        // });
     }
  });





})