package com.example.userproject.rest.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersResponse {
    private List<UserDto> users;
}
