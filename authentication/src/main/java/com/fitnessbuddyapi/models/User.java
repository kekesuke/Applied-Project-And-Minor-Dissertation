package com.fitnessbuddyapi.models;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames = {"email"})
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new java.util.LinkedHashSet<>();
	

	/*
	 * @ManyToMany(mappedBy = "senderUser") private Set <MessageModel> sentMessage;
	 */

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/*
	 * public Set<MessageModel> getMessages() { return sentMessage; }
	 * 
	 * public void setMessages(Set<MessageModel> messages) { this.sentMessage =
	 * messages; }
	 */
}