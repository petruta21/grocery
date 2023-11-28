package com.tatsiana.grocery.service;

import com.tatsiana.grocery.dto.UserDto;
import com.tatsiana.grocery.model.User;

public interface UserService {
    User createUser(UserDto userDto);

    User findByUsername(String username);

    User save(User user);
}
