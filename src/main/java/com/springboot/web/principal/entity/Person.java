package com.springboot.web.principal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int age;

	private String gender;

	private String code;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
	private Date created_at;
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "information_person", joinColumns = { @JoinColumn(name = "person_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ident_id") })

	private List<Identification> identification;

	public void addIdentification(Identification identifications) {
		if (this.identification == null) {
			this.identification = new ArrayList<>();
		}

		this.identification.add(identifications);
	}

//	private Set<Identification> identification = new HashSet<>();

	public Person(Long id, String name, int age, String gender, String code, Date created_at) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.code = code;
		this.created_at = created_at;
	}

	public Person(String name, int age) {

		this.name = name;
		this.age = age;
	}

	public Person() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age; 
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", code=" + code
				+ ", created_at=" + created_at + "]";
	}

}
