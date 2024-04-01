package com.portal.jobconnect.Posts;

import org.springframework.stereotype.Component;

@Component
public class Posts {
    private String id;
    private String title;
    private String location;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
