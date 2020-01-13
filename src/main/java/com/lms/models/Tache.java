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
@Table(name= "tache")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Tache implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;

	private String nom;
	private String description;
	private String deadline;
	private String priorite;

	@OneToMany(fetch=FetchType.LAZY , mappedBy="TacheEmployee")
	@JsonIgnore
	private Set<TacheEmployee> employee = new HashSet<TacheEmployee>(); 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Projet projet;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tache")
	@JsonIgnore
	private Set<Commentaire> commentaire = new HashSet<Commentaire>();

	public Tache(String nom, String description, String deadline, String priorite, Projet projet) {
		this.nom = nom;
		this.description = description;
		this.deadline = deadline;
		this.priorite = priorite;
		this.projet = projet;
	}
	public Tache(){}
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
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getPriorite() {
		return priorite;
	}
	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public Set<Commentaire> getCommentaire() {
		return commentaire;
	}
	
	public Set<TacheEmployee> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<TacheEmployee> employee) {
		this.employee = employee;
	}
	public void setCommentaire(Set<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}
	
	
}
