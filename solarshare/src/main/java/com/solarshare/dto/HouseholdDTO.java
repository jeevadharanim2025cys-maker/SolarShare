package com.solarshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HouseholdDTO {

    private Long id;

    @NotBlank(message = "Household name is required")
    @Size(max = 100, message = "Household name must not exceed 100 characters")
    private String householdName;

    @NotNull(message = "User ID is required")
    private Long userId;

    public HouseholdDTO() {
    }

    public HouseholdDTO(Long id, String householdName, Long userId) {
        this.id = id;
        this.householdName = householdName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}