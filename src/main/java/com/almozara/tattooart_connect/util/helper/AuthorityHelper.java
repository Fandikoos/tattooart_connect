package com.almozara.tattooart_connect.util.helper;

public final class AuthorityHelper {
    public static final String ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";
    public static final String ROLE_USER = "hasAuthority('ROLE_USER')";
    public static final String ANY_ROLE = "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')";

}
