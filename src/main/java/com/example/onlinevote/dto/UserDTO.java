package com.example.onlinevote.dto;

import com.example.onlinevote.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	int id;
	String name;
	String surname;
	String username;
	String interests;
	String password;
	String role;
	String group;

	public UserDTO(User user) {
		setId(user.getId());
		setName(user.getName());
		setSurname(user.getSurname());
		setUsername(user.getUsername());
		setRole(user.getRole().getName());
		setInterests(user.getInterests());
		setPassword(user.getPassword());
		setGroup(user.getGroup().getName());
	}
}
