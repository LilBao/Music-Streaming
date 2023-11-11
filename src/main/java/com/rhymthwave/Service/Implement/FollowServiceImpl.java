package com.rhymthwave.Service.Implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.FollowDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.FollowService;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Follow;

import jakarta.transaction.Transactional;

@Service
public class FollowServiceImpl implements CRUD<Follow, Long>, FollowService{
	
	@Autowired
	FollowDAO dao;

	@Override
	@Transactional
	public Follow create(Follow follow) {
		follow.setFollowDate(new Date());
		Follow dataFollow = dao.save(follow);
		return dataFollow;
	}

	@Override
	@Transactional
	public Follow update(Follow follow) {
		Follow dataFollow = dao.save(follow);
		return dataFollow;
	}

	@Override
	@Transactional
	public Boolean delete(Long key) {
		dao.deleteById(key);
		return null;
	}

	@Override
	public Follow findOne(Long key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Follow> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Follow snapFollow(Follow follow,Author accountA, Author accountB) {
		follow.setAuthorsAccountA(accountA);
		follow.setAuthorsAccountB(accountB);
		return follow;
	}
	
	@Override
	public Follow findFollowByAccount(Author accountA, Author accountB) {
		return dao.findFollowByAccount(accountA, accountB);
	}
	
	

	@Override
	public List<Follow> findMyListFollow(Author accountA) {
		return dao.findByAuthorsAccountA(accountA);
	}

	@Override
	public List<Follow> findYourListFollow(Long authorId) {
		return dao.findByAuthorsAccountB(authorId);
	}
	
}
