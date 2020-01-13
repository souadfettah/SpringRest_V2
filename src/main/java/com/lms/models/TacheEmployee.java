package com.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tache_employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TacheEmployee implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id ;
	
	private static final long serialVersionUID = 1L;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="done")
	private boolean done;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private Employee EmployeeTache; 
	
	@ManyToOne(fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	@JsonIgnore
	private Tache TacheEmployee;
	
	public Employee getEmployeeTache() {
		return EmployeeTache;
	}
	public void setEmployeeTache(Employee employeeTache) {
		EmployeeTache = employeeTache;
	}

	public Tache getTacheEmployee() {
		return TacheEmployee;
	}

	public void setTacheEmployee(Tache tacheEmployee) {
		TacheEmployee = tacheEmployee;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
}
