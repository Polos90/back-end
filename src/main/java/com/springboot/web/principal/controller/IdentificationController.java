package com.springboot.web.principal.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.principal.entity.Identification;
import com.springboot.web.principal.entity.Person;
import com.springboot.web.principal.repository.IdentificationRepository;

@RestController
@RequestMapping("/ident")
public class IdentificationController {
	@Autowired
	IdentificationRepository identificationRepository;

	@GetMapping("/getIdent")
	public List<Identification> allIdent() {
		return identificationRepository.findAll();
	}

	@GetMapping("/{id}")
	public Identification getNoteById(@PathVariable(value = "id") Long id) {
		return identificationRepository.findById(id).orElse(null);
	}

	@PostMapping("/create")
	public Identification crear(@Param("identificationName") String identificationName,
			@Param("descripcion") String descripcion, @Param("created") String created,
			@Param("name") String name, @Param("gender") String gender, @Param("age") int age,
			@Param("code") String code, @Param("createdAt") Date createdAt) {

		Identification ident = new Identification();
		Person person = new Person();
		
		ident.setIdentificationName(identificationName);
		ident.setDesc(descripcion);
		ident.setCreated(created);

		person.setName(name);
		person.setAge(age);
		person.setCode(code);
		person.setCreatedAt(createdAt);
		person.setGender(gender);
		
		ident.addPerson(person);
		
		identificationRepository.save(ident);
		return ident;
	}

	@PutMapping("/update/{id}")
	public Identification updateIdent(@PathVariable Long id, @Validated @RequestBody Identification identDetails) {
		Identification i = identificationRepository.findById(id).orElse(null);

		i.setIdentificationName(identDetails.getIdentificationName());
		i.setDesc(identDetails.getDesc());
		i.setCreated(identDetails.getCreated());

		Identification updatedIdent = identificationRepository.save(i);

		return updatedIdent;

	}

	@DeleteMapping("/delete/{id}")
	public void deleteIdent(@PathVariable Long id) {

		Identification ident = identificationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Ident Id:" + id));
		identificationRepository.delete(ident);

	}
}