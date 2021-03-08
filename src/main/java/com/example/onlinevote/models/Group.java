package com.example.onlinevote.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "group_id")
	private List<User> userList;

	public Group(String name) {
		this.name = name;
		this.userList = new ArrayList<>();
	}

	public Group() {
	}

	public void addUser(User user) {
		this.userList.add(user);
	}

	public void removeUser(User user) {
		this.userList.removeIf(e -> e.getId() == user.getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Group))
			return false;
		Group group = (Group) o;
		return getId() == group.getId() && Objects.equals(getName(), group.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
