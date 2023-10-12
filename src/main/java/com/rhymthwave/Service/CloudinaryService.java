package com.rhymthwave.Service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	Map<String,Object> Upload(MultipartFile file, String parentFolder, String childFolder);
	Map<String,Object> UploadResizeImage(MultipartFile file, String parentFolder, String childFolder, Integer width, Integer Height);
	List<String> uploadMultipleFiles(MultipartFile[] files, String parentFolder, String childFolder);
	Boolean deleteFile (String publicID);
	String readLrc(String lrcUrl);
}
