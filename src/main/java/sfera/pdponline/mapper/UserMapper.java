package sfera.pdponline.mapper;

import org.springframework.stereotype.Component;
import sfera.pdponline.entity.Users;
import sfera.pdponline.payload.response.ResUser;

@Component

public class UserMapper {

    public ResUser resUser(Users user ){
        return ResUser.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .imageUrl(user.getImage())
                .role(user.getRole() != null ? user.getRole().getRole().name() : null)
                .build();
    }


}
