package com.loptur.gateway.config

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

class KeycloakRoleConverter: Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(source: Jwt): Collection<GrantedAuthority> {
        val realmAccess = source.claims["realm_access"] as Map<*, *>?
        val roles = realmAccess?.get("roles") as List<*>?

        val authorities = mutableListOf<GrantedAuthority>()
        roles?.forEach { role ->
            authorities.add(GrantedAuthority { "ROLE_$role" })
        }

        return authorities
    }

}