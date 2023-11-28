package com.tatsiana.grocery.controller;

import com.tatsiana.grocery.dto.RecaptchaDto;
import com.tatsiana.grocery.dto.UserDto;
import com.tatsiana.grocery.service.UserService;
import com.tatsiana.grocery.service.impl.GoogleRecaptchaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoogleRecaptchaServiceImpl captchaService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/adduser")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, HttpServletRequest httpServletRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        String response = httpServletRequest.getParameter("g-recaptcha-response");
        if (response == null) {
            return "add-user";
        }
        String ip = httpServletRequest.getRemoteAddr();
        RecaptchaDto recaptchaDto = captchaService.verify(ip, response);
        if (!recaptchaDto.isSuccess()) {
            return "redirect:adduser?incorrectCaptcha";
        }
        try {
            userService.createUser(userDto);
            return "redirect:adduser?success";
        } catch (DataIntegrityViolationException e) {
            return "redirect:adduser?duplicate";
        }
    }
}
