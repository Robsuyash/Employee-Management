package com.suyash.Employee.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suyash.Employee.Management.Exception.UserNotFoundException;
import com.suyash.Employee.Management.Model.User;
import com.suyash.Employee.Management.Repo.UserRepo;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepo.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User update(@RequestBody User newuser, @PathVariable Long id) {
        return userRepo.findById(id).map(user -> {
            user.setName(newuser.getName());
            user.setUsername(newuser.getUsername());
            user.setEmail(newuser.getEmail());
            return userRepo.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return "User with ID " + id + " deleted successfully.";
    }

}
