package com.ck.fileupload.ver1;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UploadValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return UploadCommand.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		UploadCommand uploadCommand = (UploadCommand) arg0;
		
		if(!StringUtils.hasLength(uploadCommand.getTitle())){
			arg1.rejectValue("title", "required");
		}
		
		if(!StringUtils.hasLength(uploadCommand.getName())){
			arg1.rejectValue("name", "required");
		}		

		if(uploadCommand.getUpFile().isEmpty() == true){
			arg1.rejectValue("upFile", "required");
		}				
	}	
}
