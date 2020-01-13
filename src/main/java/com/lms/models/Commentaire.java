package com.lms.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Commentaire implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String type;
	private String message;

	@ManyToOne
	@JsonIgnore
	private Employee employee;

	@ManyToOne
	@JsonIgnore
	private LeaveDetails tache;

	@CreationTimestamp
	private Date createdAt;


	public Commentaire(String type, String message, Employee employee, LeaveDetails tache) {
		this.type = type;
		this.message = message;
		this.employee = employee;
		this.tache = tache;
	}

	public Commentaire() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return type;
	}
	public void setTitre(String titre) {
		this.type = titre;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LeaveDetails getTache() {
		return tache;
	}
	public void setTache(LeaveDetails tache) {
		this.tache = tache;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
