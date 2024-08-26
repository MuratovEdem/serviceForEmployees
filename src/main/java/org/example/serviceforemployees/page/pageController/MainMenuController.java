package org.example.serviceforemployees.page.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/main-menu")
public class MainMenuController {

    @GetMapping
    public String getMenuPage() {
        return "main-menu-page.html";
    }
}
