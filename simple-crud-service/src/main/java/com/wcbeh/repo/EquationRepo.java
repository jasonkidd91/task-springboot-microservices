package com.wcbeh.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wcbeh.entity.Equation;

public interface EquationRepo extends CrudRepository<Equation, Long> {
	public Optional<Equation> findOneByValueXAndValueYAndValueZ(Integer valueX, Integer valueY, Integer valueZ);
}
