package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Category;

public interface CategoryService {

	List<Category> SearchMedia(String keyword);
}
