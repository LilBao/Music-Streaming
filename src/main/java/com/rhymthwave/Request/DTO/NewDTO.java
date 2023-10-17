package com.rhymthwave.Request.DTO;

import org.springframework.web.multipart.MultipartFile;

public record NewDTO(String title, String content, String ImageLocation, MultipartFile img) {

}
