package com.lms.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Service implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(mappedBy="service",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Employee> employee = new HashSet<Employee>();


	@OneToMany(mappedBy="service",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Projet> projet = new HashSet<Projet>();

	public Set<Projet> getProjet() {
		return projet;
	}

	public void setProjet(Set<Projet> projet) {
		this.projet = projet;
	}

	public Service(String name) {
		this.name = name;
	}
	public Service() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}


	
	
	

}
