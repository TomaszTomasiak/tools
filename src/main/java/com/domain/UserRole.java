package com.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "UserRoles")
public class UserRole implements Serializable {

	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "type")
	private String type;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "userRolesList")
	private Set<User> userSet = new HashSet<>();

	public UserRole() {
		super();
	}

	public UserRole(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}


}
