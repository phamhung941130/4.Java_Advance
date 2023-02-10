package com.vti.entity;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"), MANAGER("MANAGER"), EMPLOYEE("EMPLOYEE");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public static Role toEnum(String sqlValue) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(sqlValue)) {
                return role;
            }
        }
        return null;
    }

    public String getValueOf(String value) {
        return value;
    }
}
