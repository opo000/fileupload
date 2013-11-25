package com.ck.fileupload.ver1;

import org.springframework.web.multipart.MultipartFile;

public class UploadCommand {

	private String title;
	private String name;
	private MultipartFile upFile;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getUpFile() {
		return upFile;
	}
	public void setUpFile(MultipartFile upFile) {
		this.upFile = upFile;
	}
}
