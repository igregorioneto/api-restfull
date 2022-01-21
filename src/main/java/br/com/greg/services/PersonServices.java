package br.com.greg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.greg.converter.DozerConverter;
import br.com.greg.data.vo.PersonVO;
import br.com.greg.exception.ResourceNotFoundException;
import br.com.greg.model.Person;
import br.com.greg.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parserObject(person, Person.class);
		var vo = DozerConverter.parserObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		var entity = repository.findById(
				person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parserObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parserObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		return DozerConverter.parserObject(repository.findAll(), PersonVO.class);
	}
}
