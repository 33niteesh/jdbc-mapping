package com.jdbc_mapping.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="emp")
public class Employee {

    @Id
    @Column(name="empid")
    private Long empid;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cntid", referencedColumnName = "cntid")
    private Contact contact;
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Employee() {
		super();
	}
	public Employee(Long empid, String name) {
		super();
		this.empid = empid;
		this.name = name;
	}
	
	

    
}
