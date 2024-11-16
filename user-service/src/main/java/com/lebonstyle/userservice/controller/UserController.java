package com.lebonstyle.userservice.controller;

import com.lebonstyle.userservice.entity.User;
import com.lebonstyle.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations related to user management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a User", description = "Add a new User to the database")
    public ResponseEntity<User> createUser(@RequestBody User User) {
        userService.createUser(User);
        return ResponseEntity.status(HttpStatus.CREATED).body(User);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean isDeleted = userService.deleteUserByUuid(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserByUuid(id));
    }

    @PutMapping("/uuid/{uuid}")
    @Operation(summary = "Update a User by UUID", description = "Update an existing User using its UUID")
    public ResponseEntity<User> updateUserByUuid(@PathVariable UUID uuid, @RequestBody User updatedUser) {
        Optional<User> User = userService.updateUserByUuid(uuid, updatedUser);
        return User.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
