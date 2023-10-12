//package com.rhymthwave.Service.Implement;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.rhymthwave.DAO.MoodDAO;
//import com.rhymthwave.Service.CRUD;
//import com.rhymthwave.entity.Mood;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class MoodServiceImpl implements CRUD<Mood, Integer>{
//
//	@Autowired
//	MoodDAO dao;
//	
//	@Override
//	@Transactional
//	public Mood create(Mood entity) {
//		if(entity!=null) {
//			dao.save(entity);
//			return entity;
//		}
//		return null;
//	}
//
//	@Override
//	@Transactional
//	public Mood update(Mood entity) {
//		if(entity!=null) {
//			dao.save(entity);
//			return entity;
//		}
//		return null;
//	}
//
//	@Override
//	@Transactional
//	public Boolean delete(Integer key) {
//		if(key instanceof Integer && key>0) {
//			dao.deleteById(key);
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public Mood findOne(Integer key) {
//		if(key instanceof Integer && key>0) {
//			return dao.findById(key).get();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Mood> findAll() {
//		return dao.findAll();
//	}
//}
