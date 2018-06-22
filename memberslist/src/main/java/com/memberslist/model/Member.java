package com.memberslist.model;

import java.io.File;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "members")
public class Member {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private Date birthdate;
	private Integer postalcode;
	private File picture;

	public Member() {
	}

	@JsonCreator
	public Member(@JsonProperty("firstname") String firstname, @JsonProperty("lastname") String lastname,
			@JsonProperty("birthdate") Date birthdate, @JsonProperty("postalcode") int postalcode,
			@JsonProperty("picture") File picture) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.postalcode = postalcode;
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(Integer postalcode) {
		this.postalcode = postalcode;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", birthdate=" + birthdate
				+ ", postalcode=" + postalcode + ", picture=" + picture + "]";
	}

}
