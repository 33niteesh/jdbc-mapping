package com.jdbc_mapping.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cnt")
public class Contact {
	@Id
    @Column(name="cntid")
    private Long cntcid;
    public Long getCntcid() {
		return cntcid;
	}
	public void setCntcid(Long cntcid) {
		this.cntcid = cntcid;
	}
	private String email;
    private String message;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Contact(Long cntid,  String email, String message) {
		super();
		this.cntcid = cntid;
		this.email = email;
		this.message = message;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}