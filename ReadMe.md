# Online Job Portal - Full Stack Application

## Overview

This repository houses the source code for a full-stack job portal application built with React for the frontend and Spring Boot, Hibernate, and MongoDB for the backend. It offers a user-friendly platform for job seekers and employers to connect effectively.

## Features

### For Job Seekers (Employee):
1. Create and manage profiles
2. Search for jobs based on various criteria (location, keyword, etc.)
3. Apply for available positions
4. Track application status

### For Job Posters (Employer):
1. Post job openings
2. Manage and review applications

## Technologies

1. Frontend: React
2. Backend: Spring Boot, Java, Hibernate
3. Database: PostgreSQL

## Application Strucuture
```
src/main/java/com
└── portal
    └── jobconnect
        ├── application
        │   └── ServerPortLister.java
        ├── controller
        │   ├── HomeController.java
        │   ├── PostController.java
        │   └── ProfileController.java
        ├── JobconnectApplication.java
        ├── model
        │   ├── Post.java
        │   ├── Profile.java
        │   └── ResponseObject.java
        ├── service
        │   ├── PostService.java
        │   └── ProfileService.java
        └── utils
            ├── AppConfig.java
            ├── Constants.java
            └── DatabaseConfig.java
```