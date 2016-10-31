package io.smsc.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username","email"}, name = "users_unique_username_email_idx")})
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "User's name cannot be empty")
    private String username;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "User's password cannot be empty")
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "User's first name cannot be empty")
    private String firstName;

    @Column(name = "surname", nullable = false)
    @NotEmpty(message = "User's surname cannot be empty")
    private String surName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty(message = "User's email cannot be empty")
    private String email;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "created", columnDefinition = "timestamp default now()")
    private Date created = new Date();

    @Column(name = "blocked", nullable = false)
    private boolean blocked = true;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Role> roles;

    public User() {
    }

    public User(Integer id, String username, String password, String firstName, String surName, String email, boolean active, Date created, boolean blocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.active = active;
        this.created = created;
        this.blocked = blocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", created=" + created +
                ", blocked=" + blocked +
                ", roles=" + roles +
                '}';
    }
}