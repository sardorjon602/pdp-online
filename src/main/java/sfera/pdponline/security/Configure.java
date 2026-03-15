package sfera.pdponline.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import sfera.pdponline.entity.Users;
import sfera.pdponline.exceptions.NotFoundException;
import sfera.pdponline.payload.ApiResponse;
import sfera.pdponline.repository.UserRepository;

@Configuration
@RequiredArgsConstructor

public class Configure {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Users users = userRepository.findByEmail(username).get();
            if (users == null) {
                throw new NotFoundException(new ApiResponse("User Not Found", false, HttpStatus.NOT_FOUND, null ));

            }else  {
                return users;
            }
        };
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsPasswordService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
