package com.example.EcoSwap.controller;

import com.example.EcoSwap.entity.User;
import com.example.EcoSwap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
    
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "auth/register";
        }
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "auth/register";
        }
        userService.createUser(user);
        return "redirect:/login?registered";
    }
}
