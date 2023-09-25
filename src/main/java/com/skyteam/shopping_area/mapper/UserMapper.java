package com.skyteam.shopping_area.mapper;

import com.skyteam.shopping_area.dto.UpdateUserDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "image", expression = "java(user.getImage() != null ? user.getImage().getUrl() : \"\")")
    UserDto userToUserDto(User user);

    UpdateUserDto userToUpdateUserDto(User user);
}
