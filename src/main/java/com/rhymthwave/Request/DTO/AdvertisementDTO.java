package com.rhymthwave.Request.DTO;

import com.rhymthwave.entity.Subscription;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class AdvertisementDTO {
    private String title;
    private String content;
    private String tag;
    private String url;
    private Integer subscription;
    private  MultipartFile image;
    private  MultipartFile audio;

}
