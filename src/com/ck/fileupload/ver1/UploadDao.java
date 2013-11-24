package com.ck.fileupload.ver1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class UploadDao {
	
	private FileOutputStream fos;
	
	public void writeFile(MultipartFile file, String path, String fileName){
		try {
			
			byte fileDate[] = file.getBytes();
			fos = new FileOutputStream(path + File.separator + fileName);
			fos.write(fileDate);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fos != null){
				try{
					fos.close();
				}catch(Exception err){}
			}
		}
		
	}
	

}
