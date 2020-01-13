package com.lms.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projet")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Projet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nom;
	private String description;
	private String date_creation;
	private Date date_debut;
	private Date date_fin;
	private String budget;
	@Column(name = "accept_reject_flag")
	private boolean acceptRejectFlag;
	@Column(name = "active")
	private boolean active;

	public boolean isAcceptRejectFlag() {
		return acceptRejectFlag;
	}

	public void setAcceptRejectFlag(boolean acceptRejectFlag) {
		this.acceptRejectFlag = acceptRejectFlag;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JsonIgnore
	private Service service;

	public Projet(String nom, Service service) {
		this.nom = nom;
		this.service = service;
	}

	@OneToMany (mappedBy="projet",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<LeaveDetails> leaveDetails = new HashSet<LeaveDetails>();

	public Set<LeaveDetails> getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(Set<LeaveDetails> leaveDetails) {
		this.leaveDetails = leaveDetails;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee  ;

	public Projet(String nom, String description, String date_creation, Date date_debut, Date date_fin, String budget, Service service) {
		this.nom = nom;
		this.description = description;
		this.date_creation = date_creation;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.budget = budget;
		this.service = service;
	}
	public Projet() {

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}



	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	


	
}
