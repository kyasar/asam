package com.kyasar.model;

import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection="users")
public class User {

	@Id
	private String _id;

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@JsonIgnore
	private Set<String> roles = new HashSet<String>();

	public User(
			@JsonProperty("name") String name,
			@JsonProperty("surname") String surname,
			@JsonProperty("username") String username,
			@JsonProperty("password") String password) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.roles.add("ROLE_USER");
		if (name.equalsIgnoreCase("kadir"))	// for test only
			this.roles.add("ROLE_ADMIN");
	}

	public User(User user) {
		super();
		this._id = user.get_id();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	public User() {

	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
