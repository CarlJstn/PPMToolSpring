package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.example.demo.validations.ValidPassword;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Entity
public class User {

	// Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // First Name
    @NotBlank(message = "First Name is required")
    private String firstName;
    
    // Middle Name
    private String middleName;
    
    // Last Name
    @NotBlank(message = "Last Name is required")
    private String lastName;
   
    // Password
    @NotBlank(message = "Password is required")
    @ValidPassword
    private String password;
    
    // Confirm Password
    @NotBlank(message = "Password confirmation is required") 
    private String passwordConfirm;
    
    // Project Identifier
    @NotBlank(message = "Username is required")
    @Size(min=4, max=5, message ="Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String username;
    
    // Created and Updated Dates
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false, nullable=false)
    private Date created_At;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = true)
    private Date updated_At;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }
    
    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }
    
    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }

}
