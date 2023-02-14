package com.wcbeh.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcbeh.entity.Equation;
import com.wcbeh.exception.CrudErrorException;
import com.wcbeh.repo.EquationRepo;
import com.wcbeh.service.CrudService;
import com.wcbeh.util.Utils;

@Service
public class CrudServiceImpl implements CrudService {
	@FunctionalInterface private interface Function<X, Y, Z, SUM> {
	    public SUM apply(X x, Y y, Z z);
	}
	
	private static final Function<Integer, Integer, Integer, Integer> equation = (x, y, z) -> (x + y - z);
	private static final Logger logger = LoggerFactory.getLogger(CrudService.class);
	
	@Autowired private EquationRepo equationRepo;
	@Autowired private ExecutorService executor;
	/****************************************************
	 * Public Methods
	 ***************************************************/
	@Override
	public void create(Set<Integer> numbers) {
		/**
		 * Using Set to avoid duplications in the inputs
		 * (refer to constraints duplication not allowed)
		 */
		logger.info("input: " + numbers.toString());
		if(numbers.size() < 3) {
			logger.error("validation failed: parameters must consists at least of 3 non-repetition numbers!");
			throw new CrudErrorException("parameters less than 3 distinct numbers!");
		}
		/**
		 * By referring the test's question, equation is [x + y - z]
		 * therefore generating the possible permutation combination of set of 3 numbers
		 * each number in the set will be representing corresponding to the value x, y and z
		 */
		logger.info("generating permutation combination...");
		List<int[]> combinations = new ArrayList<>();
		int[] intArrays = numbers.stream().mapToInt(Integer::new).toArray();
		Utils.permutation(intArrays, new int[3], 0, combinations);
		logger.info("combinations size: " + combinations.size());
	    /**
	     * Put all possible combinations into thread to processing
	     * save to database if fulfilled condition after applied equation
	     * else if condition not fulfilled, skip
	     */
		try {
			batchProcessing(combinations);
		} catch (InterruptedException e) {
			throw new CrudErrorException(e.getLocalizedMessage());
		}
	}
	
	@Override
	public List<Equation> read() {
		return StreamSupport.stream(equationRepo.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void delete() {
		equationRepo.deleteAll();
	}
	/****************************************************
	 * Private Methods
	 ***************************************************/
	@Transactional
	private void batchProcessing(List<int[]> combinations) throws InterruptedException {
		logger.info("preparing tasks for batch processing...");
		// add runnable thread as task into a list to be prepared for process
		List<Callable<Object>> tasks = new ArrayList<Callable<Object>>();
	    for (int[] combination : combinations) {
	    	Runnable task = () -> {
	    		long threadStartTime = System.currentTimeMillis();
	    		String value = "int" + Arrays.toString(combination);
	    		logger.info(value + ": start execute");
	    		int x = combination[0];
	    		int y = combination[1];
	    		int z = combination[2];
	    		int total = equation.apply(x, y, z);
	    		if(total >= 0 && total < 20) {
	    			if(equationRepo.findOneByValueXAndValueYAndValueZ(x, y, z).isPresent()) {
	    				logger.info(value + ": total is " + total + ", existed in db!");
	    			} else {
	    				logger.info(value + ": total is " + total + ", save into db!");
	    				Equation equation = new Equation(x, y, z);
	    				equationRepo.save(equation);
	    			}
	    		} else {
	    			logger.info(value + ": total is " + total + ", skip!");
	    		}
	    		logger.info(value + ": end execute in " + (System.currentTimeMillis() - threadStartTime) + "ms");
	    	};
	    	tasks.add(Executors.callable(task)); 
	    }
	    logger.info("starting batch processing, batch tasks size: " + tasks.size());
	    long batchStartTime = System.currentTimeMillis();
	    // start execution task list
	    executor.invokeAll(tasks);
	    logger.info("batch process completed in " + (System.currentTimeMillis() - batchStartTime) + "ms");
	}
		
}
