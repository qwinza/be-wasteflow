package com.wasteflow.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wasteflow.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nama;
    private String email;

    @JsonIgnore
    private String password;

    private Long locationId;
    private String locationName;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String nama, String email, String password,
                           Long locationId, String locationName,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.locationId = locationId;
        this.locationName = locationName;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        Long locationId = user.getLocation() != null ? user.getLocation().getId() : null;
        String locationName = user.getLocation() != null ? user.getLocation().getNamaLokasi() : null;

        return new UserDetailsImpl(
                user.getId(),
                user.getNama(),
                user.getEmail(),
                user.getPassword(),
                locationId,
                locationName,
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String getUsername() {
        return email; // Use email as username
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
