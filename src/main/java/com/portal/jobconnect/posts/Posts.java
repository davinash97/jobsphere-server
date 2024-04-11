package com.portal.jobconnect.posts;

import org.springframework.stereotype.Component;

@Component
public class Posts {
    public String _id;
    public String title;
    public String location;
    public String description;

    public Posts() {
    };

    public Posts(String _id, String description) {
        this._id = _id;
        this.description = description;
    }

    public Posts(String _id, String title, String description, String location) {
        this._id = _id;
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
