package br.com.greg.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.greg.data.vo.v2.PersonVOV2;
import br.com.greg.model.Person;

@Service
public class PersonConverter {
	
	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVOtoEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return entity;
	}

}
