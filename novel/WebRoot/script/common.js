function loadurl(url,value){

	if(url!=null&&url!=""){

		if(url.indexOf("?")>0){

			url=url+"&page="+value;

		}else{

			url=url+"?page="+value;

		}

		window.location=url;

	}
}


$(function () {
	$(window).scroll(function(){  
		$scrollBottom = $(document).height() - $(window).height() - $(window).scrollTop();
	    if ($(window).scrollTop()>=250 ) {
	    	// $("#tb").show();
	    	$("#picTop").fadeIn('slow');
	    }else{
	    	$("#picTop").fadeOut('slow');
	    }

	    if ( $scrollBottom>=250) {
	    	// $("#tb").show();
	    	$("#picBottom").fadeIn('slow');
	    }else{
	    	$("#picBottom").fadeOut('slow');
	    }

	})

	$("#picTop").click(function(event) {
		$('html ,body').animate({scrollTop: 0}, 1000);
			return false;
	});

	$("#picBottom").click(function(event) {
		$("html,body").animate({
	                    scrollTop: document.body.clientHeight
	                },1000);
		return false;
	});

})
