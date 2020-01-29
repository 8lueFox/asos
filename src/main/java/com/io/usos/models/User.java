package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 36)
    private String username;

    @NotBlank
    @Size(min = 2, max = 126)
    private String password;

    @Transient
    private String passwordConfirm;

    @NotBlank
    private String imie;

    @NotBlank
    private String nazwisko;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    private boolean enabled;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    public User(String username, String password, String passwordConfirm, String imie, String nazwisko, Role role){
        this.username = username;
        this.password = password;
        this.passwordConfirm = password;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.role = role;
        this.enabled = true;
    }

    public Set<Role> getRoles(){
        Set<Role> roles = new HashSet<>();
        roles.add(getRole());
        return roles;
    }
}
