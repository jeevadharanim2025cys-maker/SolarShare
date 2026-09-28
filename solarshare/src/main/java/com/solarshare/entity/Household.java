package com.solarshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "households")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String householdName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Household() {
    }

    public Household(String householdName, User user) {
        this.householdName = householdName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}