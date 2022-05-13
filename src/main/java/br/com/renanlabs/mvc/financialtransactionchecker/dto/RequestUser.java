package br.com.renanlabs.mvc.financialtransactionchecker.dto;

import javax.validation.constraints.NotNull;

import br.com.renanlabs.mvc.financialtransactionchecker.model.User;

public class RequestUser {
	private Long id;

	@NotNull(message = "Name is mandatory")
	private String name;

	
	@NotNull(message = "E-mail is mandatory")
	private String email;

	
	public RequestUser() {
		// TODO Auto-generated constructor stubpode
	}

	// converting User to RequestUser
	public RequestUser(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public User toUser() {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
