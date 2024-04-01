package com.portal.jobconnect.UserAccounts.Employee;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private String name;
    private String expertise;
    private int experience;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    public String getExpertise() {
        return this.expertise;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getExperience() {
        return this.experience;
    }

}
