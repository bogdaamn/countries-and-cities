package com.cities.type;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_EDITOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
