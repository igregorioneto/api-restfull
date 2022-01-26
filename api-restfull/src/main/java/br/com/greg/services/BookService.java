package br.com.greg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.greg.converter.DozerConverter;
import br.com.greg.data.vo.BookVO;
import br.com.greg.exception.ResourceNotFoundException;
import br.com.greg.model.Book;
import br.com.greg.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parserObject(book, Book.class);
		var vo = DozerConverter.parserObject(entity, BookVO.class);
		return vo;
	}
	
	public BookVO update(BookVO book) {
		var entity = repository.findById(
				book.getId()).orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parserObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book book = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		repository.delete(book);
	}
	
	public BookVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		var vo = DozerConverter.parserObject(entity, BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parserObject(repository.findAll(), BookVO.class);
	}

}
