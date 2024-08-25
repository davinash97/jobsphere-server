  # Online Job Portal – Full Stack Application
<!-- TOC -->
* [Online Job Portal – Full Stack Application](#online-job-portal--full-stack-application)
  * [Overview](#overview)
  * [Features](#features)
    * [For Job Seekers (Employee)](#for-job-seekers-employee)
    * [For Job Posters (Employer)](#for-job-posters-employer)
  * [Technologies](#technologies)
  * [Installation and Setup](#installation-and-setup)
    * [Prerequisites](#prerequisites)
    * [Steps](#steps)
  * [Endpoints](#endpoints)
    * [Profile](#profile)
    * [Profile with {profileId}](#profile-with-profileid)
    * [Post](#post)
    * [User-specific posts for {profileId}](#user-specific-posts-for-profileid)
    * [Specific post actions for {profileId} and {postId}](#specific-post-actions-for-profileid-and-postid)
* [Application Structure](#application-structure)
* [Credits](#credits)
<!-- TOC -->
## Overview

This repository houses the source code for a full-stack job portal application built with React for the Front end
and Spring Boot, Hibernate, and Postgres for the Back end. It offers a user-friendly platform for job seekers
and employers to connect effectively.

## Features

### For Job Seekers (Employee)
1. Create and manage profiles
2. Search for jobs based on various criteria (job description, location, keyword, etc.)
3. Apply for available positions
4. Track application status

### For Job Posters (Employer)
1. Post job openings
2. Manage and review applications

## Technologies

1. Frontend: React
2. Backend: Spring Boot, Java, Hibernate
3. Database: Postgres

## Installation and Setup

### Prerequisites
- Java 22
- Maven 3+
- Docker (optional, for containerized deployment)

### Steps
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/JobSphere/server.git
   cd server
   ```
2. **Configure Environment Variables:**

   Copy the config.env to .env and set your environment variables.


3. **Build and Run the Application**

   `mvn spring-boot:run`


4. **Run with Docker**

    docker-compose up

## Endpoints

### Profile
| Method | Endpoint  |         Detail          |
|:------:|:---------:|:-----------------------:|
|  POST  | /profile  | Create a new profile    |

### Profile with {profileId}
| Method |        Endpoint         |          Detail           |
|:------:|:-----------------------:|:-------------------------:|
|  GET   | /profile/{profileId}    | Retrieve profile details  |
|  PUT   | /profile/{profileId}    | Update existing profile   |
| DELETE | /profile/{profileId}    | Delete a profile          |

### Post
| Method | Endpoint |       Detail        |
|:------:|:--------:|:-------------------:|
|  GET   |  /post   | Retrieve all posts  |

### User-specific posts for {profileId}
| Method |         Endpoint           |             Detail             |
|:------:|:--------------------------:|:------------------------------:|
|  GET   | /profile/{profileId}/post  | Get all posts for a profile    |
|  POST  | /profile/{profileId}/post  | Create a new post for profile  |

### Specific post actions for {profileId} and {postId}
| Method |              Endpoint               |                 Detail                 |
|:------:|:-----------------------------------:|:--------------------------------------:|
|  PUT   | /profile/{profileId}/post/{postId}  | Update a specific post for a profile   |
| DELETE | /profile/{profileId}/post/{postId}  | Delete a specific post for a profile   |

# Application Structure
```
src/main/java
└── com
    └── portal
        └── jobsphere
            ├── application
            │   └── ServerPortListener.java
            ├── config
            │   ├── AppConfig.java
            │   ├── CacheConfig.java
            │   ├── DatabaseConfig.java
            │   ├── ExecutorConfig.java
            │   └── WebSocketConfig.java
            ├── controller
            │   ├── HomeController.java
            │   ├── PostController.java
            │   └── ProfileController.java
            ├── enums
            │   ├── Gender.java
            │   └── Role.java
            ├── exception
            │   └── CustomException.java
            ├── executor
            │   └── Execute.java
            ├── JobsphereApplication.java
            ├── model
            │   ├── Message.java
            │   ├── Post.java
            │   ├── Profile.java
            │   └── ResponseObject.java
            ├── repository
            │   ├── MessageRepository.java
            │   ├── PostRepository.java
            │   └── ProfileRepository.java
            ├── service
            │   ├── MessageService.java
            │   ├── PostService.java
            │   └── ProfileService.java
            └── utils
                └── Constants.java
```


# Credits
Credits to [Niharika](https://github.com/Niharika2803) (a.k.a. NightFlick) for project idea and other features suggestion(s).