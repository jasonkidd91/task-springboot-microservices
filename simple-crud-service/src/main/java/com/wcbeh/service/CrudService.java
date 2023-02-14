package com.wcbeh.service;

import java.util.List;
import java.util.Set;

import com.wcbeh.entity.Equation;

public interface CrudService {
	void create(Set<Integer> numbers);
	List<Equation> read();
	void delete();
}
