package br.com.greg.controllers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PagedResourcesAssemblerArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.greg.data.vo.PersonVO;
import br.com.greg.data.vo.v2.PersonVOV2;
import br.com.greg.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin
@Api(value = "Person Endpoint", description = "Description for persons", tags = {"PersonEndpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices services;
	
	@ApiOperation(value = "Search for a person")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {
		Optional<PersonVO> personVO = Optional.of(services.findById(id));
		if (!personVO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			personVO.get().add(linkTo(methodOn(PersonController.class).findById(id)).withRel("List of persons"));
			return new ResponseEntity<PersonVO>(personVO.get(), HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Disabled a specific person by your ID")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PersonVO> disablePerson(@PathVariable("id") Long id) {
		Optional<PersonVO> personVO = Optional.of(services.disablePerson(id));
		if (!personVO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			personVO.get().add(linkTo(methodOn(PersonController.class).findById(id)).withRel("List of persons"));
			return new ResponseEntity<PersonVO>(personVO.get(), HttpStatus.OK);
		}
	}
	
//	@CrossOrigin(origins = {"http://localhost:8080"})
	@ApiOperation(value = "Find all people recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Page<PersonVO>> findAll(
				@RequestParam(value = "page", defaultValue = "0") int page,
				@RequestParam(value = "limit", defaultValue = "12") int limit,
				@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> persons = services.findAll(pageable);
		if(persons.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for(PersonVO person : persons) {
				long id = person.getId();
				person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
			}
			
			return new ResponseEntity<>(persons, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Register a person")
	@PostMapping(
			produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" } 
			)
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return services.create(PersonVO);
	}
	
	@ApiOperation(value = "Register a person v2")
	@PostMapping(
			value = "/v2",
			produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" }
			)
	public PersonVOV2 create(@RequestBody PersonVOV2 person) {
		return services.createV2(person);
	}
	
	@ApiOperation(value = "Update a person's record")
	@PutMapping(
			produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" }
			)
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return services.update(PersonVO);
	}
	
	@ApiOperation(value = "Delete a person")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
