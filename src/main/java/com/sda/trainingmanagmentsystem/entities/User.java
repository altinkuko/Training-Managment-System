package com.sda.trainingmanagmentsystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull
    @NotEmpty(message = "Must have a Name")
    private String firstName;
    private String lastName;
    @Email(message = "Not a valid email address")
    private String email;
    @NotNull
    @NotEmpty(message = "Required field")
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
    private Set<UserNotification> userNotifications;

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
    @AssertTrue(message = "Password must contain at least 4 characters")
    public boolean validPassword() {
        return password.length() > 3 && !password.isBlank();
    }
}
