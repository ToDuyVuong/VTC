package hcmute.tlcn.vtc.configuration;

import hcmute.tlcn.vtc.authentication.AuthenticationService;
import hcmute.tlcn.vtc.authentication.RegisterRequest;
import hcmute.tlcn.vtc.entity.extra.Role;
import hcmute.tlcn.vtc.repository.CustomerRepository;
import hcmute.tlcn.vtc.util.exception.InvalidPasswordException;
import hcmute.tlcn.vtc.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> customerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại."));
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        Date currentDate = new Date();
        return args -> {
            var us1 = RegisterRequest.builder()
                    .username("user1")
                    .fullName("Nguyen")
                    .email("example@example.com")
                    .password("password")
                    .birthday(currentDate)
//                    .gender(true)
                    .phone("0123456789")
                    .role(Role.CUSTOMER)
                    .build();
            System.out.println("user1 token: " + service.register(us1));



        };
    }

    public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

        public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
            setUserDetailsService(userDetailsService);
            setPasswordEncoder(passwordEncoder());
        }

        @Override
        protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
            if (!passwordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
                throw new InvalidPasswordException("Sai mật khẩu.");
            }
        }
    }
}
