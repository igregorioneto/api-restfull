package br.com.greg.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.greg.data.vo.BookVO;
import br.com.greg.data.vo.PersonVO;
import br.com.greg.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Book Endpoint", description = "Description for books", tags = {"BookEndpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookService services;
	
	@ApiOperation(value = "Get a book")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<BookVO> findById(@PathVariable("id") Long id) {
		Optional<BookVO> bookVO = Optional.of(services.findById(id));
		if(!bookVO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			bookVO.get().add(linkTo(methodOn(BookController.class).findAll()).withRel("List of persons"));
			return new ResponseEntity<>(bookVO.get(),HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Search through all books")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<List<BookVO>> findAll() {
		List<BookVO> booksVO = services.findAll();
		if(booksVO.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for(BookVO book : booksVO) {
				long id = book.getId();
				book.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
			}
			return new ResponseEntity<List<BookVO>>(booksVO,HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Register a book")	
	@PostMapping(
			produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" } 
		)
	public BookVO create(@RequestBody BookVO book) {
		return services.create(book);
	}
	
	@ApiOperation(value = "Update a book")
	@PutMapping(
			produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" } 
		)
	public BookVO update(@RequestBody BookVO book) {
		return services.update(book);
	}
	
	@ApiOperation(value = "Delete a book")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
