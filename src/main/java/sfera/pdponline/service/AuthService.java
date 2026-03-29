package sfera.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sfera.pdponline.entity.Users;
import sfera.pdponline.entity.enums.Role;
import sfera.pdponline.exceptions.NotFoundException;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.payload.request.AuthLogin;
import sfera.pdponline.payload.request.AuthRegister;
import sfera.pdponline.payload.response.Token;
import sfera.pdponline.repository.RoleRepository;
import sfera.pdponline.repository.UserRepository;
import sfera.pdponline.security.JWTProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender javaMailSender;
    private final JWTProvider jWTProvider;

    public ApiResponse register(AuthRegister authRegister){
        boolean exists = userRepository.existsByEmailAndRole_Role(authRegister.getEmail(), Role.ROLE_USER);
        if(exists){
            return new ApiResponse("This email has been used before.",false, HttpStatus.BAD_REQUEST,null);

        }
        long code = Math.round(Math.random()*1000000);
//        long code = (long)(Math.random() * 9000) + 1000;
        System.out.println(code);

        Users user = Users.builder()
                .fullName(authRegister.getFullName())
                .email(authRegister.getEmail())
                .phoneNumber(authRegister.getPhoneNumber())
                .password(passwordEncoder.encode(authRegister.getPassword()))
                .image(null)
                .role(roleRepository.findByRole(Role.ROLE_USER))
                .enabled(false)
                .code(code)
                .build();
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("email");
        message.setTo(authRegister.getEmail());
        message.setSubject("Verify your account");
        message.setText("Your code "+code+"\n Enter this code.");
        javaMailSender.send(message);

        return new ApiResponse("You have registered, now enter the code",true,HttpStatus.OK,null);
    }

    public ApiResponse activateUser(Long code){
     Users users = userRepository.findByCode(code).orElse(null);
     if(users==null){
         return new ApiResponse("User not found",false,HttpStatus.NOT_FOUND,null);
     }
     users.setEnabled(true);
     users.setCode(null);
     userRepository.save(users);

        String token = jWTProvider.generateToken(users.getEmail());
        Token tokenObj = Token.builder()
                .token(token)
                .role(users.getRole().getRole().name())
                .build();
        return new ApiResponse("You have activated your account",true,HttpStatus.OK,tokenObj);
    }


    public ApiResponse login(AuthLogin authLogin){
        Users users = userRepository.findByEmail(authLogin.getEmail()).orElse(null);
        if(users==null){
            return new ApiResponse("User not found",false,HttpStatus.NOT_FOUND,null);
        }
        if (users.isEnabled()) {
            if (passwordEncoder.matches(authLogin.getPassword(), users.getPassword())) {

                String token = jWTProvider.generateToken(users.getEmail());
                Token tokenObj = Token.builder()
                        .token(token)
                        .role(users.getRole().getRole().name())
                        .build();
                return new ApiResponse("You have logged in",true,HttpStatus.OK,tokenObj);
            }
            return new ApiResponse("You have not logged in",false,HttpStatus.BAD_REQUEST,null);
        }
        return new ApiResponse("You are not active ",false,HttpStatus.BAD_REQUEST,null);
    }

}
