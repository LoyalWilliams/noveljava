var KSW = KSW || {version: '1.0.0'};
KSW.uid = 0,
KSW.bid = 0,
KSW.getinfo = function(){		//用户信息
    var api_info = ks_host + "/usercenter/getUserinfo";
	if(typeof ks_uid != "undefined")KSW.uid = ks_uid;
	if(typeof ks_bkid != "undefined")KSW.bid = ks_bkid;
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_info,
		data: {uid: KSW.uid},
        success: function(data) {
            var json = data,
                request = json.result.status.code,
                msg = json.result.status.msg;
            if(request == 0){
                KSW.money.balance = json.result.data.usergold;
                KSW.flower.balance = json.result.data.userflower;
				KSW.money.int();
    			KSW.flower.int();
            }
        }
    });
},
KSW.tips = KSW.tips || {},
KSW.tips.show = function(a,t){
    var t = t*1000 || 2*1000;
    $(".pop_cover").show().css({"opacity":0.5});
    $(".pop_tip").show();
    $(".pop_cont").html(a);
    setTimeout(KSW.tips.hide,t)
},
KSW.tips.hide = function(){
    $(".pop_tip").fadeOut();
    $(".pop_cover").fadeOut();
},
KSW.sign = KSW.sign || {},		//签到
KSW.sign.day = 0,
KSW.sign.active = false,
KSW.sign.confirm = function(o){
    var html = '';
    if(KSW.sign.active){
        $(o).parent().remove();
        return
    }
    var api_sign = ks_host + "/usercenter/modsign";
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_sign,
        data: {uid: KSW.uid},
        success: function(data) {
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
				var score = json.result.data.signcount || 0;
				var days = json.result.data.signday || 0;
				var sum = json.result.data.score || 0;
				var max = json.result.data.maxvalue || 0;
				var gradename = json.result.data.gradename || "";
				var dom = $("#usr_grade");
				html = "签到成功! +"+score+"<br>已连续"+days+"天签到";
				dom.find(".gradename").html(gradename);
				dom.find(".exp_get").html(sum);
				dom.find(".exp_tag").html(max);
                KSW.sign.active = true;
                $(o).parent().remove();
            }else{
                html = '<p class="t_c">'+ msg +'</p>';
            }
            KSW.tips.show(html,2);
        }
    });
},
KSW.sign.int = function(){
    $("#btn_sign").on("click",function(){KSW.sign.confirm(this);return !1});
},
KSW.bookrack = KSW.bookrack || {},		/* 整理书架 */
KSW.bookrack.active = false;
KSW.bookrack.show = function(o){
    var pl = o.parent(), sl = pl.siblings();
    o.html("完成");
    sl.each(function(){
        $(this).find(".binfo").show().on("click",function(){
			var link = $(this).find("a");
            var obj = $(this).parent();
            var cid = link.attr("data-cid");
			if(link.hasClass("clt_del"))KSW.collect.del(cid,obj);
			if(link.hasClass("rec_del"))KSW.recent.del(cid,obj);
			return !1;
        })
    });
    KSW.bookrack.active = true;
},
KSW.bookrack.hide = function(o){	
    var pl = o.parent(), sl = pl.siblings();
    o.html("整理");
    sl.each(function(){
        $(this).find(".binfo").hide()
    });
    KSW.bookrack.active = false;
},
KSW.bookrack.int = function(){
    $(".btn_rec, .btn_ord").on("click",function(e){
        var op = $(this), pl = op.parent(), sl = pl.siblings();
        if(!op.data("active")){
            KSW.bookrack.show(op);
            op.data("active",true);
        }else{
            KSW.bookrack.hide(op);
            op.data("active",false);
        }
    });
},
KSW.recent = KSW.recent || {},
KSW.recent.del = function(cid,obj){    //删除最近阅读
    if(!cid || !obj)return;
    var html = '';
    var api_delrec = ks_host + "/usercenter/delH5read";
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_delrec,
        data: {articleid:cid, uid: KSW.uid},
        success: function(data){
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
//                html = '<p class="t_c">'+ "成功删除" +'</p>';
                $(obj).remove();
            }else{
                html = '<p class="t_c">'+ msg +'</p>';
            	KSW.tips.show(html,2);
            }
        }
    });
},
KSW.collect = KSW.collect || {},
KSW.collect.hascolect = false,
KSW.collect.submit = function(o){    //收藏图书
	if(!KSW.uid){window.location.href = ks_host+ "/login"; return};
    var html = '成功收藏至我的书架';
    var api_collect = ks_host + "/usercenter/h5collect";
	if($(o).hasClass("active"))return;
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_collect,
        data: {articleid:KSW.bid, uid:KSW.uid},
        success: function(data) {
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
				$(o).addClass("active").html("已收藏");
            }else{
                html = '<p class="t_c">'+ msg +'</p>';
			}
            KSW.tips.show(html,2);
        }
    });
},
KSW.collect.del = function(cid,obj){    //删除收藏
    if(!cid || !obj)return;
    var html = '';
    var api_delclt = ks_host + "/usercenter/delH5collect";
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_delclt,
        data: {articleid:cid, uid:KSW.uid},
        success: function(data) {
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
//                html = '<p class="t_c">'+ "成功删除收藏" +'</p>';
                $(obj).remove();
            }else{
                html = '<p class="t_c">'+ msg +'</p>';
            	KSW.tips.show(html,2);
            }
        }
    });
},
KSW.collect.int = function(){
    $("#btn_fav").on("click",function(){KSW.collect.submit(this);return !1});
},
KSW.commend = KSW.commend || {},
KSW.commend.submit = function(id,obj){    //评论点赞
    if(!id || !obj)return;
	if(!$(obj).hasClass("active"))return;
    var api_commend = ks_host + "/comment/h5ding";
	var html = "";
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_commend,
        data: {id:id, uid:KSW.uid},
        success: function(data) {
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
				var count = json.result.data.count;
                $(obj).find(".num_stat").html(count);
            }
            $(obj).removeClass("active");
        }
    });
},
KSW.commend.int = function(){
    $("#comment span.btn_fav").each(function(){
		$(this).on("click",function(){
			var id = $(this).attr("data-cid");
			KSW.commend.submit(id,this)
			return !1
		})
	});
},
KSW.money = KSW.money || {},		//打赏
KSW.money.balance = 0,
KSW.money.getbalance = function(){
    return KSW.money.balance;
},
KSW.money.int = function(){
	if(!KSW.money.getbalance()){
		KSW.money.lack();
		return;
	}
    $("#btn_money").on("click",KSW.money.confirm);
	$("#user_mny").html(KSW.money.getbalance()+"金币");
},
KSW.money.check = function(s){
    var bl = KSW.money.balance;
    if(bl-s>=0)return 1;
    else return !1;
},
KSW.money.lack = function(){
    $("#money_btn").hide();
    $("#money_tip").show();
},
KSW.money.confirm = function(){    
    var html = '';
	var api_money = ks_host + "/usercenter/modh5reward";
	var money = $("#smoney").val();
	if(!KSW.money.check(money))return;
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_money,
        data: {articleid:KSW.bid,num:money,uid:KSW.uid},
        success: function(data){
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
				KSW.money.balance-=money;
				html = "打赏成功<br>+"+money+"积分";
				$("#user_mny").html(KSW.money.balance+"金币");
            }else{
                html = '<p class="t_c">'+ msg +'</p>';
            }
            KSW.tips.show(html,1.5);
			if(!KSW.money.balance)KSW.money.lack();
        }
    });
    return !1;
},
KSW.flower = KSW.flower || {},		//送花
KSW.flower.balance = 0,
KSW.flower.getbalance = function(){
	if(KSW.flower.balance)return KSW.flower.balance;
	else return !1;
},
KSW.flower.check = function(s){
    var bl = KSW.flower.balance;
    if(bl-s>=0)return 1;
    else return !1;
},
KSW.flower.lack = function(){
    $("#flower_btn").hide();
    $("#flower_tip").show();
},
KSW.flower.confirm = function(){    
    var html = '';
	var api_flower = ks_host + "/usercenter/modh5flower";
	var flower = $("#sflower").val();
	if(!KSW.flower.check(flower))return;
    $.ajax({
        type: "GET",
        dataType: "jsonp",
        url: api_flower,
        data: {articleid:KSW.bid,num:flower,uid:KSW.uid},
        success: function(data){
            var json = data,
                request = json.result.status.code,
                msg     = json.result.status.msg;
            if(request == 0){
				KSW.flower.balance-=flower;
				html = '送花成功<br>+'+(flower*5)+'积分';
				$("#user_flw").html(KSW.flower.balance+"朵");
            }else{
                html = '<p class="t_c">'+ msg +'</p>';
            }
            KSW.tips.show(html,1.5);
			if(!KSW.flower.getbalance())KSW.flower.lack();
        }
    });
},
KSW.flower.int = function(){
	if(!KSW.flower.getbalance()){
		KSW.flower.lack();
		return;
	}
    $("#btn_flower").on("click",KSW.flower.confirm);
	$("#user_flw").html(KSW.flower.getbalance()+"朵");
},
KSW.gotop = function(){
    $("html,body").scrollTop(0);
},
KSW.getinfo();
;$(function(){
	$("img")
    /*tab pannel*/
    $(".tab-nav a").click(function(){
        var index = $(this).index(),
            panes = $(this).parent().parent().next(".tab-pane");
        $(this).addClass("cur").siblings().removeClass("cur");
        panes.find(".tab-content").eq(index).addClass("active")
        .siblings().removeClass("active");
        return !1; 
    });
    /*tab menu*/
    $(".tab-menu a").on("click",function(){
        var index = $(this).index(),
            panes = $(this).parent().next(".tab-pane");
        $(this).addClass("cur").siblings().removeClass("cur");
        panes.find(".tab-content").eq(index).addClass("active")
        .siblings().removeClass("active");
//        return !1;
    });
    
    /* 充值方式选择 */
    $(".radio_box").each(function(){
        $(this).on("click",function(){
            var ps = $(this).siblings();
            $(this).addClass("select").find(".btn_radio").attr("checked",true);
            ps.removeClass("select");
            ps.find(".btn_radio").attr("checked",false)
        })
    });
    $("#gotop").on("click",KSW.gotop);
    KSW.bookrack.int();
    KSW.collect.int();
    KSW.sign.int();
	KSW.commend.int();
});
