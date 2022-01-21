package br.com.greg.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.greg.data.vo.PersonVO;
import br.com.greg.data.vo.v2.PersonVOV2;
import br.com.greg.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices services;
	
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}
	
	@GetMapping()
	public List<PersonVO> findAll() {
		return services.findAll();
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return services.create(PersonVO);
	}
	
	@PostMapping("/v2")
	public PersonVOV2 create(@RequestBody PersonVOV2 person) {
		return services.createV2(person);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return services.update(PersonVO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
