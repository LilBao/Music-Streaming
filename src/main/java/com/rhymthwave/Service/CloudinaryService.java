package com.rhymthwave.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	Map<String,Object> Upload(MultipartFile file, String parentFolder, String childFolder);
	Map<String,Object> UploadResizeImage(MultipartFile file, String parentFolder, String childFolder, Integer width, Integer Height);
	Boolean deleteFile (String publicID);
	String readLrc(String lrcUrl);
}
