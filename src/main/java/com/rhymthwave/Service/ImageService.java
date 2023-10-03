package com.rhymthwave.Service;

import com.rhymthwave.entity.Image;

public interface ImageService {
	Image getEntity(String assetid, String url, Integer weight, Integer height);
}
