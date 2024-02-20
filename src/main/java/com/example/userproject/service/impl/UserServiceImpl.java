package com.example.userproject.service.impl;

import com.example.userproject.entity.User;
import com.example.userproject.enums.ErrorCodeEnum;
import com.example.userproject.exception.CustomNotFoundException;
import com.example.userproject.repository.UserRepository;
import com.example.userproject.rest.model.request.CreateUserRequest;
import com.example.userproject.rest.model.request.UpdateUserRequest;
import com.example.userproject.rest.model.response.GetAllUsersResponse;
import com.example.userproject.rest.model.response.UserDto;
import com.example.userproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getAllUsers() {
        List<UserDto> userDtoList = userRepository.findAll()
                .stream()
                .map(user -> convertToDto(user)).collect(Collectors.toList());

        return makeUserResponse(userDtoList);
    }

    @Override
    public UserDto getUserById(long id) {
        return userRepository.findById(id)
                .map(user -> convertToDto(user))
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));
    }

    @Override
    public GetAllUsersResponse getUserByNameAndSurname(String name, String surname) {
        List<UserDto> userDtoList = userRepository.findByNameAndSurname(name, surname)
                .stream().map(user -> convertToDto(user))
                .collect(Collectors.toList());

        return makeUserResponse(userDtoList);
    }

    @Override
    public void create(CreateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userRepository.save(user);
    }

    @Override
    public void update(long id, UpdateUserRequest request) {
        User user = fetchUserIfExistsById(id);
        BeanUtils.copyProperties(request, user);
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = fetchUserIfExistsById(id);
        userRepository.delete(user);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private GetAllUsersResponse makeUserResponse(List<UserDto> userDtoList) {
        return GetAllUsersResponse.builder()
                .users(userDtoList)
                .build();
    }

    private User fetchUserIfExistsById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));
    }
}
