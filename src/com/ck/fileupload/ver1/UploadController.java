package com.ck.fileupload.ver1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.util.WebUtils;

public class UploadController extends SimpleFormController{

	Log log = LogFactory.getLog(getClass());
	
	private UploadDao uploadDao;
	
	public void setUploadDao(UploadDao uploadDao) {
		this.uploadDao = uploadDao;
	}

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		// TODO Auto-generated method stub
		
		UploadCommand uploadCommand = (UploadCommand)command;
		
		MultipartFile upFile = uploadCommand.getUpFile();
		String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/upload");
		uploadDao.writeFile(upFile, path, upFile.getOriginalFilename());
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("upload", uploadCommand);
		view.setViewName("/ver1/completedUpload"); //file 8
		
		return view;
	}

	
}
