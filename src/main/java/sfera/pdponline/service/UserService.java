package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Users;
import sfera.pdponline.entity.enums.Role;
import sfera.pdponline.mapper.UserMapper;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.AuthRegister;
import sfera.pdponline.payload.request.ReqUser;
import sfera.pdponline.payload.response.ResUser;
import sfera.pdponline.payload.response.Token;
import sfera.pdponline.repository.RoleRepository;
import sfera.pdponline.repository.UserRepository;
import sfera.pdponline.security.JWTProvider;

@Service
@RequiredArgsConstructor


public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final JWTProvider jWTProvider;

    public ApiResponse getMe(Users user){

        return ApiResponse.builder()
                .message("success")
                .status(HttpStatus.OK)
                .success(true)
                .body(userMapper.resUser(user))
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
                .role(roleRepository.findByRole(Role.ROLE_TEACHER))
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

    public ApiResponse updateUser(Users user, ReqUser  reqUser){
        if (user.getRole().getRole().equals(Role.ROLE_ADMIN)) {
            if (reqUser.getId() == null){
                boolean exists = userRepository.existsByEmailAndRole_RoleAndIdNot(reqUser.getEmail(), Role.ROLE_ADMIN, user.getId());
                if(exists){
                    return ApiResponse.builder()
                            .message("This email already exists.")
                            .success(false)
                            .status(HttpStatus.BAD_REQUEST)
                            .body(null)
                            .build();
                }

                user.setFullName(reqUser.getFullName());
                user.setPhoneNumber(reqUser.getPhoneNumber());

                if (!reqUser.getEmail().equals(user.getEmail()) ) {
                    user.setEmail(reqUser.getEmail());
                    Users save = userRepository.save(user);
                    String token = jWTProvider.generateToken(save.getEmail());
                    Token token1 = Token.builder().token(token).role(Role.ROLE_ADMIN.name()).build();
                    return ApiResponse.builder()
                            .message("Successfully updated ")
                            .success(true)
                            .status(HttpStatus.OK)
                            .body(token1)
                            .build();
                }
                userRepository.save(user);
                return ApiResponse.builder()
                        .message("Successfully updated ")
                        .success(true)
                        .status(HttpStatus.OK)
                        .body(null)
                        .build();
            }else {
                user = userRepository.findById(reqUser.getId()).orElse(null);
                if (user == null) {
                    return ApiResponse.builder()
                            .message("User not found.")
                            .success(false)
                            .status(HttpStatus.BAD_REQUEST)
                            .body(null)
                            .build();
                }

                user.setFullName(reqUser.getFullName());
                user.setPhoneNumber(reqUser.getPhoneNumber());
                user.setEmail(reqUser.getEmail());

                    userRepository.save(user);
                    return ApiResponse.builder()
                            .message("Success ")
                            .success(true)
                            .status(HttpStatus.OK)
                            .body(null)
                            .build();
            }
        }else {
            user.setFullName(reqUser.getFullName());
            user.setPhoneNumber(reqUser.getPhoneNumber());
            if (!reqUser.getEmail().equals(user.getEmail()) ) {
                user.setEmail(reqUser.getEmail());
                Users save = userRepository.save(user);
                String token = jWTProvider.generateToken(save.getEmail());
                Token token1 = Token.builder().token(token).role(user.getRole().getRole().name()).build();
                return ApiResponse.builder()
                        .message("Success")
                        .success(true)
                        .status(HttpStatus.OK)
                        .body(null)
                        .build();
            }else {
                userRepository.save(user);
                return ApiResponse.builder()
                        .message("Success")
                        .success(true)
                        .status(HttpStatus.OK)
                        .body(null)
                        .build();
            }
        }
    }


}
