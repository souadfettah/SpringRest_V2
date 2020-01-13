package com.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;
    private String sexe;
    private String date_naissance;
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY , mappedBy="employee")
    @JsonIgnore
    private List<Projet> projet ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Role role;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Service service;

    @OneToMany(mappedBy="employee", fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Commentaire> commentaire ;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="EmployeeTache")
    @JsonIgnore
    private Set<TacheEmployee> taches = new HashSet<TacheEmployee>();

    public Employee(String nom, String prenom, String email, String password, String tel, String sexe, String date_naissance, Role role, Service service) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.role = role;
        this.service = service;
    }

    public Employee() {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getDate_naissance() {
        return date_naissance;
    }
    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }
    public List<Projet> getProjet() {
        return projet;
    }
    public void setProjet(List<Projet> projet) {
        this.projet = projet;
    }
    public List<Commentaire> getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(List<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }
    public Set<TacheEmployee> getTaches() {
        return taches;
    }
    public void setTaches(Set<TacheEmployee> taches) {
        this.taches = taches;
    }


}
