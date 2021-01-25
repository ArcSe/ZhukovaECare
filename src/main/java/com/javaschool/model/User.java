package com.javaschool.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User extends AbstractModel implements UserDetails {
    @Column(name = "email")
    private String email;

    @Size(min=2, message = "Не меньше 5 знаков")
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    private boolean active;

    @OneToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
