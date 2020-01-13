package com.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "leave_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeaveDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "employee_name")
    private String employeeName;

    public String getNomTache() {
        return nomTache;
    }

    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }

    @Column(name = "nom_tache")
    private String nomTache;

    @NotNull(message = "Please provide start date!")
    @Column(name = "from_date")
    private Date fromDate;

    @NotNull(message = "Please provide end date!")
    @Column(name = "to_date")
    private Date toDate;

    @NotEmpty(message = "Please select type of leave!")
    @Column(name = "leave_type")
    private String leaveType;

    @NotEmpty(message = "Please provide a reason for the leave!")
    @Column(name = "reason")
    private String reason;

    @Column(name = "duration")
    private int duration;

    @Column(name = "accept_reject_flag")
    private boolean acceptRejectFlag;

    @Column(name = "completed")
    private String completed;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private Projet projet;
    @OneToMany(mappedBy="tache",fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<Commentaire> commentaires = new HashSet<Commentaire>();

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

    public Date getFromDate() {
	return fromDate;
    }

    public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
    }

    public Date getToDate() {
	return toDate;
    }

    public void setToDate(Date toDate) {
	this.toDate = toDate;
    }

    public String getReason() {
	return reason;
    }

    public void setReason(String reason) {
	this.reason = reason;
    }

    public String getLeaveType() {
	return leaveType;
    }

    public void setLeaveType(String leaveType) {
	this.leaveType = leaveType;
    }

    public int getDuration() {
	return duration;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

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

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public LeaveDetails() {
    }

    public LeaveDetails(String username, String employeeName, Date fromDate, Date toDate, String leaveType, String reason, int duration, boolean acceptRejectFlag, String completed, boolean active) {
        this.username = username;
        this.employeeName = employeeName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveType = leaveType;
        this.reason = reason;
        this.duration = duration;
        this.acceptRejectFlag = acceptRejectFlag;
        this.completed = completed;
        this.active = active;
    }
}
