package com.example.onlinevote.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private String surname;
	private String username;
	private String password;
	private String interests;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;

	@OneToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
}
