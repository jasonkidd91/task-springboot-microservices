package com.wcbeh.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wcbeh.entity.Equation;

@RestController
public class ApplicationController {
	@Autowired private RestTemplate restTemplate;
	/**
	 * Default Controller
	 * @return
	 */
	@GetMapping("/") public ResponseEntity<String> info() {
		return ResponseEntity.ok("search service is running...");
	}
	/**
	 * Search Controller
	 * @return
	 */
	@GetMapping("/search") public ResponseEntity<List<Equation>> search() {
		Equation[] equations = restTemplate.getForObject("http://crud-service/read", Equation[].class);
		return ResponseEntity.ok(Arrays.asList(equations));
	}
}
