package com.rhymthwave.Service;

import java.util.List;

public interface CRUD<E,T> {
	E create();
	E update();
	E delete();
	E findOne(T key);
	List<E> findAll();
}
