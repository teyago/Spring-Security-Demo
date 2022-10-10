package com.example.securitydemo.util;

import com.example.securitydemo.dto.UserDTO;
import com.example.securitydemo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Goncharov Aleksandr
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToUser(UserDTO userDTO);
}
