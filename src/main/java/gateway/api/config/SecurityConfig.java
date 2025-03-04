package gateway.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;



@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll()  // Allow login and authentication routes
                        .pathMatchers("webjars/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()  // Allow Swagger UI and OpenAPI documentation
                        .pathMatchers("/admin/**").hasAuthority("ADMIN")  // Only admin can access admin routes
                        .anyExchange().authenticated()  // Secure all other routes
                )
                .build();
    }


}
