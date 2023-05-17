package com.starter.demo.controller;

import com.starter.demo.domain.user.User;
import com.starter.demo.security.UserPrincipal;
import com.starter.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping({ "/home", "/" })
    public String userHome(HttpServletRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        User user = userService.getUserById(userPrincipal.getId());
        if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER")) {
            return "index";
        } else if (request.isUserInRole("ROLE_USER")) {
            // TODO: change this to USER home
            return "index";
        }
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        return "accessDenied";
    }
}
