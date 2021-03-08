package com.example.onlinevote.dto;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.User;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupDTO {
	private int id;
	private String name;
	private List<UserDTO> userList = new ArrayList<>();

	public GroupDTO(Group group) {
		setId(group.getId());
		setName(group.getName());
		setUsers(group.getUserList());
	}

	public GroupDTO() {
	}

	public void setUsers(List<User> users) {
		users.forEach(e -> this.userList.add(new UserDTO(e)));
	}
}
