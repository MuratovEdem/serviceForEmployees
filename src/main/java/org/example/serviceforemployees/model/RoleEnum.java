package org.example.serviceforemployees.model;

public enum RoleEnum {

    ADMIN("admin"), EMPLOYEE("employee"), REST("rest");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
