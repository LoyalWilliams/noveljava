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


