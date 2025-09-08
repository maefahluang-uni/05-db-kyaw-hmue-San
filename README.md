# Web Lab - Database Connection

We will learn about developing webservice that connect to the database, using Spring JPA Data.

## User Repository - Complete the code
Please complete the code in `User` class under `th.mfu.boot` package.
- Add `@Entity` annotation to the class
- Add `@Id` and `@GeneratedValue` to the id attribute.
In UserRepository, declare the following methods that help to find all user and find user by its username.
    - `public List<User> findAll();`
    - `public User findByUsername(String username);`

***After complete, run the unit testing in the testing view, there are three tests that need to be passed.***

- `data.sql` contains insert statements for dummy users. Find where this file is and explain what it is used for? 

```
Your answer
`data.sql` is located under `src/main/resources/`.

It’s an auto-run SQL seed script. When the Spring Boot app (or tests) start with the in-memory H2 database, Spring executes `schema.sql` (if present) to create tables and then runs `data.sql` to insert dummy rows. This preloads sample users so repository queries and unit tests have data to work with.
```

## User Reservice - Complete the code

 `UserController` class help to store the registered users. It use Java JPA to store the data in the database H2.  Please complete the source code in `UserController` class.

* Add `UserRepository` by declare it as `repo` with `@AutoWired` annotation.
* `registerUser`: This function should register a new user by adding the user to the repository. This function should be called by a `POST` request to receive the user’s JSON and return a `201 CREATED` status. If the username already exists (check by the `findByUsername` method), the function should return `409 CONFLICT`.
* `getUser`: This function should be called by a `GET` request to retrieve the JSON of the user with the given username, returning a `200 OK` status. If the user does not exist, the function should return `404 NOT FOUND`.
* `list`: This function should return a list of all registered users, with a `200 OK` status.
* `deleteUser`: This function should remove user by given `id`. It checks if the user exists. If not exists, return `404 NOT FOUND`. If exists, remove the user using `deleteById` method from the repository and return  a `200 OK` status



## Testing
Test the same way as last week's lab
### Unit Testing
Run the maven's `verify` goal.
```
mvn verify
```

***Make sure you push the code and it goes green mark***
