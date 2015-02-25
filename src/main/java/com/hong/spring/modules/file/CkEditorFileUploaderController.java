package com.hong.spring.modules.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.hong.spring.modules.file.support.CkEditorFileForm;

@Controller
@RequestMapping("/file/ckeditor/")
public class CkEditorFileUploaderController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(CkEditorFileForm fileForm, HttpServletRequest request, Model model) {

		// FIXME : 프로퍼티로 변경 시키도록 수정
		String rootPath = request.getServletContext().getRealPath("/");
		String attachPath = "img/ckeditor/";

		String fileName = "";
		MultipartFile uploadFile = fileForm.getUpload();
		if (uploadFile != null && uploadFile.isEmpty()) {
			fileName = uploadFile.getOriginalFilename();
			File dest = new File(rootPath + attachPath + fileName);
			try {
				uploadFile.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		String fileUrl = attachPath + fileName;
		model.addAttribute("fileUrl", fileUrl);
		model.addAttribute("CKEditorFuncNum", fileForm.getCKEditorFuncNum());

		return "/file/ckeditor/upload";
	}

}
