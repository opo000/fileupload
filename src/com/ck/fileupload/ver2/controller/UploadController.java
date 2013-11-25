package com.ck.fileupload.ver2.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.ck.fileupload.ver1.UploadCommand;
import com.ck.fileupload.ver1.UploadDao;
import com.ck.fileupload.ver1.UploadValidator;


@Controller
public class UploadController {

	/*
	 * Three ways to get data from the client
	 * 
	 * 1. Using RequestParam annotaion
	 * 2. MultipartHttpServletRequest - HttpRequest, MultipartRequest
	 * 3. Command object
	 * 
	 * */
	
	Log log = LogFactory.getLog(getClass());
	
	private UploadDao uploadDao;
	
	public void setUploadDao(UploadDao uploadDao) {
		this.uploadDao = uploadDao;
	}
	

	/*
	 * Two ways to save the file
	 * 1. make your own method
	 * 2. using MultipartFile method => transferTo
	 */
	
	//1
	/*
	@RequestMapping("/uploadActionVer2.spr")
    protected ModelAndView onSubmit(HttpServletRequest req, @RequestParam("title") String title, @RequestParam("name") String name, @RequestParam("upFile") MultipartFile upFile) throws IllegalStateException, IOException{
		
		String path = WebUtils.getRealPath(req.getSession().getServletContext(), "/upload");
		
		//uploadDao.writeFile(upFile, path, upFile.getOriginalFilename());
		upFile.transferTo(new File(path + File.separator + upFile.getOriginalFilename()));
		 
		UploadCommand uploadCommand = new UploadCommand();
		uploadCommand.setTitle(title);
		uploadCommand.setName(name);
		uploadCommand.setUpFile(upFile);
		
    	return new ModelAndView("/ver2/completedUpload", "upload", uploadCommand);
    }
	*/

	//2
	/*
	@RequestMapping("/uploadActionVer2.spr")
    protected ModelAndView onSubmit(MultipartHttpServletRequest req) throws IllegalStateException, IOException{
		
		String path = WebUtils.getRealPath(req.getSession().getServletContext(), "/upload");
		MultipartFile upFile = req.getFile("upFile");
		
		
		//uploadDao.writeFile(upFile, path, upFile.getOriginalFilename());
		upFile.transferTo(new File(path + File.separator + upFile.getOriginalFilename()));
		 
		UploadCommand uploadCommand = new UploadCommand();
		uploadCommand.setTitle(req.getParameter("title"));
		uploadCommand.setName(req.getParameter("name"));
		uploadCommand.setUpFile(upFile);
		
    	return new ModelAndView("/ver2/completedUpload", "upload", uploadCommand);
    }
    */	
	
	@RequestMapping("/uploadActionVer2.spr")
    protected ModelAndView onSubmit(HttpServletRequest req, @Valid UploadCommand command, BindingResult result) throws IllegalStateException, IOException{
		
		//validation check1
//		new UploadValidator().validate(command, result);  //check validation
		if(result.hasErrors()){
			return new ModelAndView("../upload2");
		}
		
		//validation check2 using @Valid and @InitBinder
		
		String path = WebUtils.getRealPath(req.getSession().getServletContext(), "/upload");
		MultipartFile upFile = command.getUpFile();

		upFile.transferTo(new File(path + File.separator + upFile.getOriginalFilename()));
		 
    	return new ModelAndView("/ver2/completedUpload", "upload", command);
    }
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new UploadValidator());
	}
	
	
	
	
}
