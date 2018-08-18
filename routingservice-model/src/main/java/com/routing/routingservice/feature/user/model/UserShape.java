package com.routing.routingservice.feature.user.model;

import com.routing.routingservice.feature.skill.model.Shape;

import java.util.Set;

public class UserShape {

    private String userId;
    private Set<Shape> shapes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(Set<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserShape [userId=");
        builder.append(userId);
        builder.append(", shapes=");
        builder.append(shapes);
        builder.append("]");
        return builder.toString();
    }

    public static final class Builder {
        private UserShape userShape;

        private Builder() {
            userShape = new UserShape();
        }

        public static Builder anUserShape() {
            return new Builder();
        }

        public Builder withUserId(String userId) {
            userShape.setUserId(userId);
            return this;
        }

        public Builder withShapes(Set<Shape> shapes) {
            userShape.setShapes(shapes);
            return this;
        }

        public UserShape build() {
            return userShape;
        }
    }
}
