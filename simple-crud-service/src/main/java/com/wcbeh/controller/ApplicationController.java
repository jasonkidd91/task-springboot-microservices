package com.wcbeh.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcbeh.entity.Equation;
import com.wcbeh.service.CrudService;

@CrossOrigin(origins = "*")
@RestController
public class ApplicationController {
	@Autowired private CrudService crudService;
	/**
	 * Default Controller
	 * @return
	 */
	@GetMapping("/") public ResponseEntity<String> info() {
		return ResponseEntity.ok("crud service is running...");
	}
	/**
	 * Create Controller
	 * @param numbers
	 */
	@PostMapping("/create") public ResponseEntity<String> create(@RequestBody Set<Integer> numbers) {
		crudService.create(numbers);
		return ResponseEntity.ok("Successful!");
	}
	/**
	 * Read Controller
	 * @return list of Equation
	 */
	@GetMapping("/read") public ResponseEntity<List<Equation>> read() {
		return ResponseEntity.ok(crudService.read());
	}
	/**
	 * Delete Controller
	 * @return
	 */
	@DeleteMapping("/delete") public ResponseEntity<String> delete() {
		crudService.delete();
		return ResponseEntity.ok("Deleted!");
	}
	
}
