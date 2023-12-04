package hcmute.tlcn.vtc.configuration;

import hcmute.tlcn.vtc.model.extra.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] NO_AUTH = {
            "/api/auth/**",
            "/api/customer/forgot-password",
            "/api/customer/reset-password",
            "/api/product/**",
            "/api/shop-detail/shop/**",
            "/api/voucher/**",
            "/api/review/**",
            "/api/comment/**",
            "/api/chat/**",
            "/api/location/**",


            "/api/payment/**",


            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private static final String[] CUSTOMER_ROLE = {
            "/api/customer/**",
            "/api/favorite-product/**",
            "/api/vendor/register/**",
            "/api/followed-shop/**",
            "/api/shop-detail/**",
            "/api/cart/**",
            "/api/order/**",

//            "/api/payment/**",


    };

    private static final String[] VENDOR_ROLE = {
            "/api/vendor/**",
    };

    private static final String[] ADMIN_ROLE = {
            "/api/admin/category/**",
            "/api/admin/brand/**",
            "/api/manager/**",


    };

    private static final String[] MANAGER_ROLE = {
            "/api/manager/**",
    };


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(NO_AUTH)
                                .permitAll()

                                .requestMatchers(CUSTOMER_ROLE)
                                .authenticated()

                                .requestMatchers(VENDOR_ROLE)
                                .hasRole(Role.VENDOR.name())

                                .requestMatchers(ADMIN_ROLE)
                                .hasRole(Role.ADMIN.name())

                                .requestMatchers(MANAGER_ROLE)
                                .hasRole(Role.MANAGER.name())

                                .anyRequest()
                                .authenticated()


                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        ;


        return http.build();
    }


}
