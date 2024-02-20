package com.example.userproject.controller;

import com.example.userproject.rest.model.response.UserDto;
import com.example.userproject.rest.model.request.CreateUserRequest;
import com.example.userproject.rest.model.request.UpdateUserRequest;
import com.example.userproject.rest.model.response.GetAllUsersResponse;
import com.example.userproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User services", description = "user services")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GetAllUsersResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "This gets user by id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserDto getUsersById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public GetAllUsersResponse getUserByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return userService.getUserByNameAndSurname(name, surname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid CreateUserRequest request) {
        userService.create(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody UpdateUserRequest request) {
        userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

}
