package com.book_share.api.auth.api;

import com.book_share.api.auth.service.AuthService;
import com.book_share.api.base.api.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public final class AuthController implements BaseController {

    private final AuthService authService;

    public AuthController(final AuthService authService1) {
        this.authService = authService1;
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isValid(
        @RequestParam("uid") final String uid,
        @RequestParam("password") final String password) {
        return ResponseEntity.ok(authService.isCredentialsValid(uid, password));
    }
}
