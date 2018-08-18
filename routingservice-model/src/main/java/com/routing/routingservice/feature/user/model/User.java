package com.routing.routingservice.feature.user.model;

import java.util.LinkedHashMap;
import java.util.Set;

import com.routing.routingservice.client.model.user.UserDTO;
import com.routing.routingservice.feature.skill.model.Shape;

public class User {

	private String id;
	private Set<Shape> shapes;
	private Integer tasksCount;

	public User() {

	}

	public User(final String id) {
		this.id = id;
	}

	public User(final String id, final Set<Shape> shapes) {
		this.id = id;
		this.shapes = shapes;
	}

	public UserDTO dto() {
		final UserDTO dto = new UserDTO();
		dto.setId(this.id);
		if (null != this.shapes) {
			dto.setShapes(new LinkedHashMap<>());
			this.shapes.forEach((shape) -> {
				dto.getShapes().put(shape.getSkillKey(), shape.getValue());
			});
		}
		return dto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(Set<Shape> shapes) {
		this.shapes = shapes;
	}

	public Integer getTasksCount() {
		return tasksCount;
	}

	public void setTasksCount(Integer tasksCount) {
		this.tasksCount = tasksCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((shapes == null) ? 0 : shapes.hashCode());
		result = prime * result + ((tasksCount == null) ? 0 : tasksCount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (shapes == null) {
			if (other.shapes != null)
				return false;
		} else if (!shapes.equals(other.shapes))
			return false;
		if (tasksCount == null) {
			if (other.tasksCount != null)
				return false;
		} else if (!tasksCount.equals(other.tasksCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", shapes=");
		builder.append(shapes);
		builder.append(", tasksCount=");
		builder.append(tasksCount);
		builder.append("]");
		return builder.toString();
	}

}
