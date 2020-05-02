package com.bz.blueauth.dto.pojo;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.bz.blueauth.dto.DataTransferObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="USER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"serialVersionUID"}, allowGetters = true)
public class UserPO extends DataTransferObject{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name="user_name" , nullable=false, length=200, unique=true)
	private String userid;
	@Column(name="email" , nullable=false, length=200, unique=true)
	@Email
	private String email;
	@Column(name="first_name", length=500)
	private String firstName;
	@Column(name="last_name", length=500)
	private String lastName;
	@Column(name="password", nullable=false, length=200)
    private String password;   

	public UserPO(){

	}

    public UserPO(String userid, String email, String password) {
		this.userid = userid;
		this.email = email;
		this.password = password;
	}
	public UserPO(String userid, String email, String password, String firstName, String lastName) {
		this.userid = userid;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserPO(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}
	
	public UserPO(Map<String,?> usrMap) {
		this.userid = (String)usrMap.get("userid");
		this.email = (String)usrMap.get("email");
		this.password = (String)usrMap.get("password");
		this.firstName = (String)usrMap.get("firstName");
		this.lastName = (String)usrMap.get("lastName");
    }
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	

	@Override
    public String toString() {
        return String.format("UserPO [userid=%s, firstName=%s, lastName=%s, email=%s]", this.userid,this.firstName,this.lastName,this.email);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

