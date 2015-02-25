package com.hong.spring.modules.file.support;

import org.springframework.web.multipart.MultipartFile;

public class CkEditorFileForm {

	private MultipartFile upload;
	private String CKEditorFuncNum;

	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

}
