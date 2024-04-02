package com.portal.jobconnect.Posts;

import org.springframework.stereotype.Component;

@Component
public class Posts {
    private String title;
    private String location;
    private String description;

    public Posts(){};
    
    public Posts(String title, String description, String location){
        this.title = title;
        this.location = location;
        this.description = description;
    };

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
