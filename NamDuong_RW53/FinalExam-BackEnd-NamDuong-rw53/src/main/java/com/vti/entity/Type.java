package com.vti.entity;

public enum Type {
    DEVELOPER("Dev"), TESTER("Test"), SCRUM_MASTER("ScrumMaster"), PROJECT_MANAGER("PM");

    private final String code;


    Type(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Type toType(String code) {
        if (code.equals("Dev")) {
            return DEVELOPER;
        }
        if (code.equals("Test")) {
            return TESTER;
        }
        if (code.equals("ScrumMaster")) {
            return SCRUM_MASTER;
        }
        if (code.equals("PM")) {
            return PROJECT_MANAGER;
        }
        throw new UnsupportedOperationException("This code is unsupported!");
    }
}
