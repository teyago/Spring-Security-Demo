# Spring Security Demo

The technology behind it:
* Java 11
* Postgres
* Spring Boot
* Spring Data
* Spring Security

---
**Quick start:**

#### Using `docker-compose`

In the terminal run the following command:
```console
$ docker-compose up
```

---

#### Using `Maven (local Postgres database)`

* configure application.properties

In the terminal run the following command:

```console
$ mvn spring-boot:run
```
---

By default, the database has two users: a user with login *user*
and password *password* and an admin
with login *admin* and password *root*

**How to use as User:**

*No auth:*

_POST_

    http://localhost:8080/create

JSON body:

    {
        "username":"test_user",
        "name":"name",
        "password":"password"
    }

user will be created with data according to json body

*Basic Auth authorization:*

_PATCH_

    http://localhost:8080/edit

JSON body:

    {
        "username":"username",
        "name":"updated_name",
        "password":"password"
    }

user data will be changed according to json body

_GET_

    http://localhost:8080/info

response will return information about user account

**How to use as Admin:**

*Basic Auth authorization:*

_GET_ 

    http://localhost:8080/info/*user_id*

response will return information about user with *user_id*

_DELETE_ 

    http://localhost:8080/delete/*user_id*

user with *user_id* will be deleted

_PATCH_

    http://localhost:8080/ban/*user_id*

user with *user_id* will be blocked

_PATCH_

    http://localhost:8080/unban/*user_id*

user with *user_id* will be unblocked

_PATCH_

    http://localhost:8080/make-admin/*user_id*

authority of the user with *user_id* will be changed to admin

_PATCH_

    http://localhost:8080/make-user/*user_id*

authority of the user with *user_id* will be changed to user

_PATCH_

    http://localhost:8080/edit/*user_id*

JSON body:

    {
        "username":"username",
        "name":"updated_name",
        "password":"password"
    }

user data with *user_id* will be changed according to json body
