package com.freitas.lucas.carregistration.domain.enums;

import java.util.Arrays;

public enum Role {

    DEFAULT (1, "ROLE_DEFAULT");

    private final int code;

    private final String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    Role(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Role toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(Role.values())
                .filter(element -> code.equals(element.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role id: " + code));
    }
}
