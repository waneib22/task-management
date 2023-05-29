package com.waneib22.taskmanagement.model;

public enum Role {

    ROLE_USER("user"),
    ROLE_ADMIN("admin"),
    ROLE_MANAGER("manager");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
