package com.portal.jobconnect.UserAccounts.Employer;

import org.springframework.stereotype.Component;

@Component
public class Employer {
    private String name;
    private String company;

    public void setName (String name) {
        this.name = name;
    }

    public void setCompany (String company) {
        this.company = company;
    }

    public String getName() {
        return this.name;
    }

    public String getCompany() {
        return this.company;
    }
}
