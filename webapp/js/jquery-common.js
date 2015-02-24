function accessDeniedException() {
	alert('접근이 거부되었습니다.');
}

function authenticationException() {
	alert('인증이 만료 되었습니다.');
}

(function($) {
	$.ajaxSetup({
		statusCode : {
			403 :  accessDeniedException
			, 401 : authenticationException
		},
		beforeSend : function(xhr) {
			//xhr.setRequestHeader("AJAX", true);
		},
		error : function(xhr, status, err) {
			if (xhr.status == 401) {
				alert("401");
			} else if (xhr.status == 403) {
				alert("403");
			} else {
				alert("예외가 발생했습니다. 관리자에게 문의하세요.");
			}
		}
	});
})(jQuery);