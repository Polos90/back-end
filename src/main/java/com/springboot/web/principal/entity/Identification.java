package com.springboot.web.principal.entity;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;


@Entity
@Table(name = "identification")
public class Identification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String identificationName;
	private String descripcion;
	private String created;

	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "identification")
	 private List<Person> listPersons;
	
	 public void addPerson(Person persons){
	        if(this.listPersons == null){
	            this.listPersons = new ArrayList<>();
	        }
	        
	        this.listPersons.add(persons);
	    }
	
	
	public Identification(Long id, String identificationName, String descripcion, String created) {
		this.id = id;
		this.identificationName = identificationName;
		this.descripcion = descripcion;
		this.created = created;
	}

	public Identification(String identificationName, String descripcion) {
		this.identificationName = identificationName;
		this.descripcion = descripcion;
	}

	public Identification() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificationName() {
		return identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
	}

	public String getDesc() {
		return descripcion;
	}

	public void setDesc(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Identification [id=" + id + ", identificationName=" + identificationName + ", descripcion=" + descripcion
				+ ", created=" + created + "]";
	}

}
