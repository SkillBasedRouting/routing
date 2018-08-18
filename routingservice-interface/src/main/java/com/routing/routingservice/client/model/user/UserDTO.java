package com.routing.routingservice.client.model.user;

import java.util.Map;

/**
 * <b>com.routing.routingservice.client.model.user.UserDTO</b>
 * <p>
 *   user with id and shapes of skills
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserDTO {

	private String id;
	private Map<String, Object> shapes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getShapes() {
		return shapes;
	}

	public void setShapes(Map<String, Object> shapes) {
		this.shapes = shapes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [id=");
		builder.append(id);
		builder.append(", shapes=");
		builder.append(shapes);
		builder.append("]");
		return builder.toString();
	}

}
