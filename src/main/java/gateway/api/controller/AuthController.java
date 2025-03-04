package gateway.api.controller;

import gateway.api.config.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Service", description = "Handles user authentication and JWT token generation")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Authenticates the user and generates a JWT token based on username and roles"
    )
    public ResponseEntity<?> login(@RequestParam String username) {
        // Hardcoded roles (in real-world, fetch from DB)
        List<String> roles = username.equals("admin") ? List.of("ADMIN") : List.of("USER");

        String token = jwtUtil.generateToken(username, roles);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
