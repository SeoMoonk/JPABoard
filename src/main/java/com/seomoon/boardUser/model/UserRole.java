package com.seomoon.boardUser.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum UserRole {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

}
