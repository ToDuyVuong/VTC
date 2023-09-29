package hcmute.tlcn.vtc.configuration;

import hcmute.tlcn.vtc.dto.user.request.RegisterCustomerRequest;
import hcmute.tlcn.vtc.service.impl.CustomerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class ApplicationConfig {

    @Bean
    public CommandLineRunner commandLineRunner(
            CustomerServiceImpl service
    ) {
        Date currentDate = new Date();
        return args -> {
            var us1 = RegisterCustomerRequest.builder()
                    .username("user1")
                    .fullName("Nguyen")
                    .email("example@example.com")
                    .password("password")
                    .birthday(currentDate)
                    .gender(true)
                    .phone("0123456789")
                    .build();
            System.out.println("Admin token: " + service.registerCustomer(us1));



        };
    }
}
