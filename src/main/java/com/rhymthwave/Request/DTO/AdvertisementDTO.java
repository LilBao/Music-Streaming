package com.rhymthwave.Request.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class AdvertisementDTO {
    private String title;
    private String content;
    private String tag;
    private String target;
    private String url;
    private  MultipartFile image;
    private  MultipartFile audio;
    private Integer priority;
    private Integer subscriptionId;
}
