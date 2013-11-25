package com.ck.fileupload.ver3.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
public class UploadController {
	
	Log log = LogFactory.getLog(getClass());

	@RequestMapping(value="/uploadActionVer3.spr", method=RequestMethod.GET)
	protected void addCnt(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String cnt = req.getParameter("addCnt");
		PrintWriter out = res.getWriter();
		out.println(cnt);
	}
	
	@RequestMapping(value="/uploadActionVer3.spr", method=RequestMethod.POST)
	protected ModelAndView onSubmit(MultipartHttpServletRequest req, UploadCommand command, BindingResult result) throws IllegalStateException, IOException{
		
		// when multi files are sent to the server, we should use MultipartHttpServletRequest or use HttpServletRequest
		List files = req.getFiles("sel[]");
		
		String path = WebUtils.getRealPath(req.getSession().getServletContext(), "/upload");
		
		for(int i=0;i<files.size();i++){
			MultipartFile upFile = (MultipartFile)files.get(i); 
			upFile.transferTo(new File(path + File.separator + upFile.getOriginalFilename()));
		}

		new UploadValidator().validate(command, result);
		
		if(result.hasErrors()){
			return new ModelAndView("../upload3");
		}
		
		command.setUpFile(files);
		
		System.out.println("===========");
		
		return new ModelAndView("/ver3/completedUpload", "upload", command);
	}	
}
