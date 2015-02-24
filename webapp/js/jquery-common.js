function forbidden(xhr, status, err) {
	alert('접근이 거부되었습니다.');
}

function unauthorized(xhr, status, err) {
	alert('인증이 만료 되었습니다.');
}

function not_found(xhr, status, err) {
	alert('페이지를 찾을 수 없습니다.');
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
		error : function(xhr, status, err) {
			if (xhr.status == 401) {
				unauthorized(xhr, status, err);
			} else if (xhr.status == 403) {
				forbidden(xhr, status, err);
			} else if (xhr.status == 404) {
				not_found(xhr, status, err);
			} else {
				alert("예외가 발생했습니다. 관리자에게 문의하세요.");
			}
		}
	});
})(jQuery);