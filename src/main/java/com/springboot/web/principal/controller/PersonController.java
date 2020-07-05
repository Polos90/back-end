package com.springboot.web.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.principal.entity.Identification;
import com.springboot.web.principal.entity.Person;
import com.springboot.web.principal.repository.PersonRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");   
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
	}
	
	
	@GetMapping("/getPerson")
	public List<Person> AllPerson() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public Person getNoteById(@PathVariable(value = "id") Long id) {
		return personRepository.findById(id).orElse(null);
	}

	@PostMapping("/create")
	public Person crear(@Param("name") String name, @Param("gender") String gender, @Param("age") int age,
			@Param("code") String code, @Param("created_at") Date created_at,
			@Param("identificationName") String identificationName,@Param("descripcion") String descripcion,
			@Param("created") String created) {

		Person person = new Person();
		Identification ident = new Identification();

		person.setName(name);
		person.setGender(gender);
		person.setAge(age);
		person.setCode(code);
		person.setCreatedAt(created_at);


		ident.setIdentificationName(identificationName);
		ident.setDesc(descripcion);
		ident.setCreated(created);
		
		person.addIdentification(ident);

		personRepository.save(person);
		return person;
	}

	@PutMapping("/update/{id}")
	public Person updatePerson(@PathVariable Long id, @Validated @RequestBody Person personDetails) {
		Person p = personRepository.findById(id).orElse(null);

		p.setName(personDetails.getName());
		p.setGender(personDetails.getGender());
		p.setCode(personDetails.getCode());
		p.setAge(personDetails.getAge());
		p.setCreatedAt(personDetails.getCreatedAt());

		Person updatedPerson = personRepository.save(p);
		return updatedPerson;

	}

	@DeleteMapping("/delete/{id}")
	public void deletePerson(@PathVariable Long id) {

		Person person = personRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
		personRepository.delete(person);

	}

}