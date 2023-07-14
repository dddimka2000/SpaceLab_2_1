package org.example.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public String handleError(Model model) {
        Object errorMessage = model.getAttribute("javax.servlet.error.message");
        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
        } else {
            model.addAttribute("error", "Unknown error occurred");
        }
        return "/error";
    }

}