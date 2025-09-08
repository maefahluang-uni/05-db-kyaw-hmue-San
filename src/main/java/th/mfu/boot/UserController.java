// package th.mfu.boot;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class UserController {

//     //TODO: add userrepository as `public` with @Autowired
//     @Autowired
//     public UserRepository repo;
   
//     @PostMapping("/users")
//     public ResponseEntity<String> registerUser(@RequestBody User user) {

//         //TODO: check if user with the username exists
       
//         //TODO: save the user

//         //TODO: remove below and return proper status
//         return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED);
//     }

//     @GetMapping("/users")
//     public ResponseEntity<List<User>> list() {
        
//         //TODO: remove below and return proper result
//         return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED);
//     }

//     @DeleteMapping("/users/{id}")
//     public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        
//         //TODO: check if user with the id exists
       
//         //TODO: delete the user
    
//         //TODO: remove below and return proper status
//         return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED);
//     }


// }


package th.mfu.boot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    public UserRepository repo;

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // username conflict?
        User existing = repo.findByUsername(user.getUsername());
        if (existing != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        }
        User saved = repo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201 + JSON
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User u = repo.findByUsername(username);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(u); // 200
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(repo.findAll()); // 200
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        repo.deleteById(id);
        return ResponseEntity.ok().build(); // 200
    }
}
