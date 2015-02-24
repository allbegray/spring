function ajaxErrorHandler(xhr, status, err) {
	if (xhr.status == 401) {
		alert('인증이 만료 되었습니다.');
		
	} else if (xhr.status == 403) {
		alert('접근이 거부되었습니다.');
		
	} else if (xhr.status == 404) {
		alert('페이지를 찾을 수 없습니다.');
		
	} else {
		alert("예외가 발생했습니다. 관리자에게 문의하세요.");
	}
}

(function($) {
	$.ajaxSetup({
//		statusCode : {
//			403 :  forbidden
//			, 401 : unauthorized
//		},
		beforeSend : function(xhr) {
			//xhr.setRequestHeader("AJAX", true);
		},
		error : ajaxErrorHandler
	});
})(jQuery);