package com.rhymthwave.ServiceAdmin.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.MoodDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IMoodService;
import com.rhymthwave.Utilities.ISort;
import com.rhymthwave.entity.Mood;

@Service
public class MoodServiceImp implements IMoodService, CRUD<Mood, Integer> {

	@Autowired
	private MoodDAO moodDAO;

	@Autowired
	private ISort<String, String> sortService;

	@Override
	public Page<Mood> getMoodPage(Integer page, String sortBy, String sortField) {

		try {
			Sort sort = sortService.sortBy(sortBy, sortField);

			Pageable pageable = PageRequest.of(page, 6, sort);

			Page<Mood> pageMood = moodDAO.findAll(pageable);
			return pageMood;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Mood create(Mood entity) {
		
		if(moodDAO.findByMoodname(entity.getMoodname()) !=null) {
			return null;
		}
		
		
		return moodDAO.save(entity);
	}

	@Override
	public Mood update(Mood entity) {
		
		Mood mood = findOne(entity.getMoodid());
		if(mood == null) {
			return null;
		}
		return moodDAO.save(entity);
	}

	@Override
	public Boolean delete(Integer key) {
		Mood mood = findOne(key);
		if(mood == null) {
			return false;
		}
		moodDAO.delete(mood);
		return true;
	}

	@Override
	public Mood findOne(Integer key) {
		Optional<Mood> mood = moodDAO.findById(key);
		if(mood.isEmpty()) {
			return null;
		}
		
		return mood.get();
	}

	@Override
	public List<Mood> findAll() {
		
		return moodDAO.findAll();
	}

}
