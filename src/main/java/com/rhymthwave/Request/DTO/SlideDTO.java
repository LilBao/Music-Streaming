package com.rhymthwave.Request.DTO;

import org.springframework.web.multipart.MultipartFile;

public record SlideDTO(String position, MultipartFile img) {

}
