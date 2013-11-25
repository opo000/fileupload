package com.ck.fileupload.ver3.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadCommand {

	private String title;
	private String name;
	private List<MultipartFile> upFile;
	
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
	public List<MultipartFile> getUpFile() {
		return upFile;
	}
	public void setUpFile(List<MultipartFile> upFile) {
		this.upFile = upFile;
	}
}
