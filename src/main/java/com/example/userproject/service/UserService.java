package com.example.userproject.service;

import com.example.userproject.rest.model.request.CreateUserRequest;
import com.example.userproject.rest.model.request.UpdateUserRequest;
import com.example.userproject.rest.model.response.GetAllUsersResponse;
import com.example.userproject.rest.model.response.UserDto;

public interface UserService {
    GetAllUsersResponse getAllUsers();

    UserDto getUserById(long id);

    GetAllUsersResponse getUserByNameAndSurname(String name, String surname);

    void create(CreateUserRequest request);

    void update(long id, UpdateUserRequest request);

    void delete(long id);
}
