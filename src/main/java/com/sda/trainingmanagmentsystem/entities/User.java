package com.sda.trainingmanagmentsystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ParticipantApplication> participantApplication;
    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private Set<Activities> modules;
    @ManyToOne
    @JoinColumn(name = "classesId")
    private Classes classes;
    @OneToMany(mappedBy = "user")
    private Set<NotificationRead> notificationReads;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
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
        return true;
    }
}
