package pl.coderslab.charity.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
