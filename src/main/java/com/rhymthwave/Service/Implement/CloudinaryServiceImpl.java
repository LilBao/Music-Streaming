package com.rhymthwave.Service.Implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.rhymthwave.Service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
	@Autowired
	Cloudinary cloudinary;
	
	@Override
	public Map<String, Object> Upload(MultipartFile file, String parentFolder, String childFolder) {
		try {
			cloudinary.api().createFolder(parentFolder, ObjectUtils.emptyMap());
			cloudinary.api().createFolder(parentFolder + "/" + childFolder, ObjectUtils.emptyMap());
			String path = parentFolder + "/" + childFolder;
			Map<String, Object> params = new HashMap<>();
			params.put("public_id", path + "/"+ file.getOriginalFilename());
			params.put("resource_type", "auto");
			Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> UploadResizeImage(MultipartFile file, String parentFolder, String childFolder,
			Integer Width, Integer Height) {
		try {
			cloudinary.api().createFolder(parentFolder, ObjectUtils.emptyMap());
			cloudinary.api().createFolder(parentFolder + "/" + childFolder, ObjectUtils.emptyMap());
			String path = parentFolder + "/" + childFolder;
			Map<String, Object> params = new HashMap<>();
			params.put("public_id", path + "/"+ file.getOriginalFilename());
			params.put("width", Width);
			params.put("height", Height);
			Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<String> uploadMultipleFiles(MultipartFile[] files, String parentFolder, String childFolder) {
		try {
			cloudinary.api().createFolder(parentFolder, ObjectUtils.emptyMap());
			cloudinary.api().createFolder(parentFolder + "/" + childFolder, ObjectUtils.emptyMap());
			String path = parentFolder + "/" + childFolder;
			List<String> uploadedUrls = new ArrayList<>();
	        
			for (MultipartFile file : files) {
	        	Map<String, Object> params = new HashMap<>();
				params.put("public_id", path + "/"+ file.getOriginalFilename());
				
	            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	            uploadedUrls.add((String) result.get("secure_url"));
	        }
			return uploadedUrls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteFile(String publicID) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("url", publicID);
			cloudinary.uploader().destroy(publicID, params);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readLrc(String lrcUrl) {
		RestTemplate restTemplate = new RestTemplate();
		String lrcContent = restTemplate.getForObject(lrcUrl, String.class);
		return lrcContent;
	}

	
	@Override
	public List<String> getCloudinaryParentFolder() {
        List<String> folderNames = new ArrayList<>();
        try {
            ApiResponse result = cloudinary.api().rootFolders(ObjectUtils.emptyMap());
            List<Map> subFolders = (List<Map>) result.get("folders");
            for (Map folder : subFolders) {
                String folderName = (String) folder.get("name");
                folderNames.add(folderName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderNames;
    }
	
	@Override
	public List<String> getCloudinaryChildFolder(String ChildFolder) {
        List<String> folderNames = new ArrayList<>();
        try {
            ApiResponse result = cloudinary.api().subFolders(ChildFolder ,ObjectUtils.emptyMap());
            List<Map> subFolders = (List<Map>) result.get("folders");
            for (Map folder : subFolders) {
                String folderName = (String) folder.get("name");
                folderNames.add(folderName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderNames;
    }


	public static String extractPublicIdFromUrl(String cloudinaryUrl) {
		Pattern pattern = Pattern.compile("/upload\\/(?:v\\d+\\/)?([^\\.]+)/");     
        Matcher matcher = pattern.matcher(cloudinaryUrl); 
        if (matcher.find()) {
            return matcher.group(1);
        }     
        return null;
    }
}
