package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Users;
import sfera.pdponline.entity.enums.Role;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.AuthRegister;
import sfera.pdponline.payload.response.ResUser;
import sfera.pdponline.repository.UserRepository;

@Service
@RequiredArgsConstructor


public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse getMe(Users user){
        ResUser resUser = ResUser.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .imageUrl(user.getImage())
                .role(user.getRole() != null ? user.getRole().getRole().name() : null)
                .build();
        return ApiResponse.builder()
                .message("success")
                .status(HttpStatus.OK)
                .success(true)
                .body(resUser)
                .build();
    }

    public ApiResponse saveTeacher(AuthRegister authRegister){
        boolean exists = userRepository.existsByEmailAndRole_Role(authRegister.getEmail(), Role.ROLE_TEACHER);
        if(exists){
            return ApiResponse.builder()
                    .message("Teacher already exists.")
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null)
                    .build();
        }

        Users user = Users.builder()
                .fullName(authRegister.getFullName())
                .email(authRegister.getEmail())
                .phoneNumber(authRegister.getPhoneNumber())
                .password(passwordEncoder.encode(authRegister.getPassword()))
                .enabled(true)
                .code(0L)
                .build();
        userRepository.save(user);
        return ApiResponse.builder()
                .message("Teacher successfully saved ")
                .success(true)
                .status(HttpStatus.OK)
                .body(null)
                .build();
    }


}
